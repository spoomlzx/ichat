package com.lan.ichat.console;

import com.lan.common.annotation.Token;
import com.lan.common.exception.IChatException;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.common.util.StringUtils;
import com.lan.ichat.model.AdminEntity;
import com.lan.ichat.service.AdminService;
import com.lan.ichat.service.TokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package com.lan.ichat.console
 * 后台的admin登录、登出、拉取管理员信息
 *
 * @author lanzongxiao
 * @date 2017/10/31
 */
@RestController
@RequestMapping(value = "/api/admin")
public class SysAdminController {
    private final static Logger logger = LoggerFactory.getLogger(SysAdminController.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public BaseResult login(@RequestBody AdminEntity admin, @Token String oldToken) {
        AdminEntity adminEntity;
        BaseResult baseResult = new BaseResult();
        // 携带token重复登录的，先删除原有token
        if (oldToken != null) {
            tokenService.delete(oldToken);
        }
        try {
            adminEntity = adminService.getAdminByUsername(admin.getUsername());
        } catch (Exception e) {
            logger.error(e.getClass().getName() + ":" + e.getMessage());
            throw new IChatException(IChatStatus.SQL_EXCEPTION);
        }
        if (adminEntity == null) {
            throw new IChatException(IChatStatus.USER_NOT_EXIST);
        }
        if (DigestUtils.sha256Hex(admin.getPassword()).equals(adminEntity.getPassword())) {
            // 使用UUID生成token
            String token = StringUtils.getUUID();
            // 将<token,userEntity>存入redis
            tokenService.set(token, adminEntity);
            baseResult.setStatus(IChatStatus.SUCCESS);
            adminEntity.setPassword(null);
            adminEntity.setToken(token);
            baseResult.setData(adminEntity);
        } else {
            throw new IChatException(IChatStatus.CREDENTIAL_INVALID);
        }
        return baseResult;
    }

    @PostMapping(value = "/logout")
    public BaseResult logout(@Token String token) {
        BaseResult baseResult = new BaseResult();
        if (token == null) {
            baseResult.setStatus(IChatStatus.TOKEN_EMPTY);
            return baseResult;
        }
        try {
            tokenService.delete(token);
            baseResult.setStatus(IChatStatus.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getClass().getName() + ":" + e.getMessage());
            baseResult.setStatus(IChatStatus.TOKEN_DEL_FAILURE);
        }
        return baseResult;
    }
}
