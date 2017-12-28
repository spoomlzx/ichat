package com.lan.ichat.console;

import com.lan.common.annotation.OpenApi;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.ichat.im.push.MessagePusher;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * package com.lan.ichat.console
 * 只有admin才能访问的api在这里实现，可以由用户访问的api都在UserController中实现
 * 管理ichat的普通用户，roleId=2
 *
 * @author lanzongxiao
 * @date 2017/11/6
 */
@RestController
@RequestMapping("/api/admin")
public class SysUserController {

    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MessagePusher messagePusher;

    @GetMapping(value = "/user")
    public BaseResult getUserList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                  @RequestParam(value = "gender", required = false) Integer gender,
                                  @RequestParam(value = "name", required = false) String name) {
        BaseResult baseResult = new BaseResult();
        try {
            HashMap<String, Object> hashMap = userService.getUserList(page, limit, name, gender);
            baseResult.setStatus(IChatStatus.GET_SUCCESS);
            baseResult.setData(hashMap);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.GET_FAILURE);
        }
        return baseResult;
    }

    @OpenApi
    @GetMapping(value = "/user/{id}")
    public BaseResult getUserInfoById(@PathVariable Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            UserEntity user = userService.getUserById(id);
            baseResult.setStatus(IChatStatus.GET_SUCCESS);
            baseResult.setData(user);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.GET_FAILURE);
        }
        return baseResult;
    }

    @PostMapping(value = "/user")
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
     * 只能由管理员才能删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/user/{id}")
    public BaseResult deleteUser(@PathVariable Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            userService.delete(id);
            baseResult.setStatus(IChatStatus.DELETE_SUCCESS);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.DELETE_FAILURE);
        }
        return baseResult;
    }

    @PostMapping(value = "/pushMessage")
    public BaseResult pushMessage(@RequestBody ChatMessage msg) {
        BaseResult baseResult = new BaseResult();
        messagePusher.push(msg);
        logger.info("ready to send");
        baseResult.setMsg("Message send success");
        baseResult.setData(msg);
        return baseResult;
    }

}
