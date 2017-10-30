package com.lan.ichat.controller;

import com.lan.common.annotation.LoginUser;
import com.lan.common.util.BaseResult;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package com.lan.ichat.controller
 *
 * @author lanzongxiao
 * @date 2017/10/30
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/info")
    public BaseResult getLoginUser(@LoginUser UserEntity user){
        BaseResult baseResult=new BaseResult("获取当前登录用户成功");
        baseResult.setData(user);
        return baseResult;
    }

}
