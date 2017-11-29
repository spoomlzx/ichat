package com.lan.ichat.console;

import com.lan.common.annotation.LoginUser;
import com.lan.common.annotation.Token;
import com.lan.common.exception.IChatException;
import com.lan.common.util.AuthResult;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.common.util.StringUtils;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.TokenService;
import com.lan.ichat.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public AuthResult login(@RequestBody UserEntity user, @Token String oldToken) {
        UserEntity userEntity;
        AuthResult authResult = new AuthResult();
        // 携带token重复登录的，先删除原有token
        if (oldToken != null) {
            tokenService.delete(oldToken);
        }
        try {
            userEntity = userService.getUserByUsername(user.getUsername(), 1);
        } catch (Exception e) {
            logger.error(e.getClass().getName() + ":" + e.getMessage());
            throw new IChatException(IChatStatus.SQL_EXCEPTION);
        }
        if (userEntity == null) {
            throw new IChatException(IChatStatus.USER_NOT_EXIST);
        }
        if (DigestUtils.sha256Hex(user.getPassword()).equals(userEntity.getPassword())) {
            // 使用UUID生成token
            String token = StringUtils.getUUID();
            // 将<token,userEntity>存入redis
            tokenService.set(token, userEntity);
            authResult.setStatus(IChatStatus.LOGIN_SUCCESS);
            userEntity.setPassword(null);
            authResult.setData(userEntity);
            authResult.setToken(token);
        } else {
            throw new IChatException(IChatStatus.CREDENTIAL_INVALID);
        }
        return authResult;
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
            baseResult.setStatus(IChatStatus.LOGOUT_SUCCESS);
        } catch (Exception e) {
            logger.error(e.getClass().getName() + ":" + e.getMessage());
            baseResult.setStatus(IChatStatus.TOKEN_DEL_FAILURE);
        }
        return baseResult;
    }

    @GetMapping(value = "/info")
    public BaseResult getLoginUser(@LoginUser UserEntity user) {
        BaseResult baseResult = new BaseResult("获取当前管理员信息成功");
        baseResult.setData(user);
        return baseResult;
    }
}