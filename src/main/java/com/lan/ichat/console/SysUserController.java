package com.lan.ichat.console;

import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.ichat.im.push.MessagePusher;
import com.lan.ichat.model.Message;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/console/user")
public class SysUserController {

    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MessagePusher messagePusher;

    @GetMapping(value = "/list")
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

    @GetMapping(value = "/info/{id}")
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

    /**
     * 只能由管理员才能删除用户
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/delete/{id}")
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
    public BaseResult pushMessage(@RequestBody Message msg) {
        BaseResult baseResult = new BaseResult();
        messagePusher.push(msg);
        baseResult.setMsg("Message send success");
        return baseResult;
    }

}
