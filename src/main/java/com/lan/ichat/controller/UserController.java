package com.lan.ichat.controller;

import com.lan.common.annotation.LoginUser;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * package com.lan.ichat.controller
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/info")
    public BaseResult getLoginUser(@LoginUser UserEntity user) {
        BaseResult baseResult = new BaseResult("获取当前登录用户成功");
        baseResult.setData(user);
        return baseResult;
    }

    @PostMapping(value = "/add")
    public BaseResult insertUser(@RequestBody UserEntity user) {
        BaseResult baseResult = new BaseResult();
        try {
            if (user.getPassword() != null) {
                user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            } else {
                user.setPassword(DigestUtils.sha256Hex("000000"));
            }
            user.setRoleId(2);
            user.setCreateTime(new Date());
            user.setEnabled(true);
            userService.insert(user);
            baseResult.setStatus(IChatStatus.INSERT_SUCCESS);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.INSERT_FAILURE);
        }
        return baseResult;
    }

    /**
     * 只能由管理员或者用户自己更新信息
     *
     * @param user
     * @param loginUser
     * @return
     */
    @PostMapping(value = "/update")
    public BaseResult updateUser(@RequestBody UserEntity user, @LoginUser UserEntity loginUser) {
        BaseResult baseResult = new BaseResult();
        if (loginUser.getRoleId() == 2 && user.getUsername() != loginUser.getUsername()) {
            baseResult.setStatus(IChatStatus.UPDATE_FAILURE);
            return baseResult;
        }
        try {
            if (user.getPassword() != null) {
                user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            }
            userService.update(user);
            baseResult.setStatus(IChatStatus.UPDATE_SUCCESS);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.UPDATE_FAILURE);
        }
        return baseResult;
    }

}
