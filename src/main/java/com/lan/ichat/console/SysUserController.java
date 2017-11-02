package com.lan.ichat.console;

import com.lan.common.annotation.LoginUser;
import com.lan.common.annotation.OpenApi;
import com.lan.common.annotation.Token;
import com.lan.common.exception.IChatException;
import com.lan.common.util.AuthResult;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.TokenService;
import com.lan.ichat.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * package com.lan.ichat.console
 *
 * @author lanzongxiao
 * @date 2017/10/31
 */
@RestController
@RequestMapping(value = "/console")
public class SysUserController {
    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);
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
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            // 将<token,userEntity>存入redis
            tokenService.add(token, userEntity);
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

    @GetMapping(value = "/user/info")
    public BaseResult getLoginUser(@LoginUser UserEntity user) {
        BaseResult baseResult = new BaseResult("获取当前登录用户成功");
        baseResult.setData(user);
        return baseResult;
    }

    @OpenApi
    @GetMapping(value = "/user/{id}")
    public BaseResult getUser(@PathVariable Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            UserEntity userEntity = userService.getUserById(id);
            baseResult.setStatus(IChatStatus.GET_SUCCESS);
            baseResult.setData(userEntity);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.GET_FAILURE);
        }
        return baseResult;
    }

    @GetMapping(value = "/user/list")
    public BaseResult getUserList() {
        BaseResult baseResult = new BaseResult();
        try {
            List<UserEntity> userList = userService.getUserList();
            baseResult.setStatus(IChatStatus.GET_SUCCESS);
            baseResult.setData(userList);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.GET_FAILURE);
        }
        return baseResult;
    }


}
