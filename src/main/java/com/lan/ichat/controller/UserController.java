package com.lan.ichat.controller;

import com.lan.common.annotation.LoginUser;
import com.lan.common.annotation.Token;
import com.lan.common.exception.IChatException;
import com.lan.common.util.*;
import com.lan.ichat.model.FriendEntity;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.TokenService;
import com.lan.ichat.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * package com.lan.ichat.controller
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 普通用户登录接口
     * 密码使用sha256Hex加密存储
     *
     * @param user
     * @param oldToken
     * @return
     */
    @PostMapping(value = "/user/login")
    public BaseResult login(@RequestBody UserEntity user, @Token String oldToken) {
        UserEntity userEntity;
        BaseResult baseResult = new BaseResult();
        // 携带token重复登录的，先删除原有token
        if (oldToken != null) {
            tokenService.delete(oldToken);
        }
        try {
            // 查找普通用户
            userEntity = userService.getUserByUsername(user.getUsername());
        } catch (Exception e) {
            throw new IChatException(IChatStatus.SQL_EXCEPTION);
        }
        if (userEntity == null) {
            throw new IChatException(IChatStatus.USER_NOT_EXIST);
        }
        if (DigestUtils.sha256Hex(user.getPassword()).equals(userEntity.getPassword())) {
            String token = StringUtils.getUUID();
            // 07/01/2018 TODO 需要优化这里的过期时间，寻找合适的方案，保证能一直刷新登录用户的token但是要使不用的token失效
            /* 将<token,userEntity>存入redis,
               设置expire=-1,表示一直不过期 */
            tokenService.set(token, userEntity, -1L);
            userEntity.setPassword(null);
            // 只在服务器控制图片服务器
            userEntity.setAvatar(ServerUtils.getAvatarUrl(userEntity.getAvatar()));
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", userEntity);
            List<FriendEntity> friends = userService.getFriendList(userEntity.getId());
            data.put("friends", friends);
            baseResult.setData(data);
            baseResult.setStatus(IChatStatus.SUCCESS);
        } else {
            throw new IChatException(IChatStatus.CREDENTIAL_INVALID);
        }
        return baseResult;
    }

    /**
     * logout 当前用户
     *
     * @param token
     * @return
     */
    @PostMapping(value = "/user/logout")
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
            baseResult.setStatus(IChatStatus.TOKEN_DEL_FAILURE);
        }
        return baseResult;
    }

    /**
     * register user
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/user/register")
    public BaseResult registerUser(@RequestBody UserEntity user) {
        BaseResult baseResult = new BaseResult();
        try {
            if (user.getPassword() != null) {
                user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            } else {
                user.setPassword(DigestUtils.sha256Hex("000000"));
            }
            user.setCreateTime(new Date());
            user.setEnabled(true);
            userService.insert(user);
            baseResult.setStatus(IChatStatus.SUCCESS);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.INSERT_FAILURE);
        }
        return baseResult;
    }

    /**
     * 获取当前登录的user
     *
     * @param user
     * @return
     */
    @GetMapping(value = "/user/info")
    public BaseResult getLoginUser(@LoginUser UserEntity user) {
        BaseResult baseResult = new BaseResult("获取当前登录用户成功");
        baseResult.setData(user);
        return baseResult;
    }

    /**
     * 该API只能由用户自己更新信息
     *
     * @param user
     * @param loginUser
     * @return
     */
    @PostMapping(value = "/user/update")
    public BaseResult updateUser(@RequestBody UserEntity user, @LoginUser UserEntity loginUser) {
        BaseResult baseResult = new BaseResult();
        // 将id设置为当前token登录的id
        user.setId(loginUser.getId());
        try {
            if (user.getPassword() != null) {
                user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            }
            userService.update(user);
            baseResult.setStatus(IChatStatus.SUCCESS);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.UPDATE_FAILURE);
        }
        return baseResult;
    }

    @GetMapping(value = "/user/fetchFriends")
    public BaseResult fetchFriendList(HttpServletRequest request) {
        BaseResult baseResult = new BaseResult("fetch friends success");
        Object obj = request.getAttribute(AuthenticationInterceptor.USER_KEY);
        logger.info("login user id: " + obj);
        List<FriendEntity> friendList = userService.getFriendList((Long) obj);
        baseResult.setData(friendList);
        return baseResult;
    }
}
