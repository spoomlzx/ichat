package com.lan.ichat.console;

import com.lan.common.annotation.OpenApi;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.ichat.im.push.MessagePusher;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * package com.lan.ichat.console
 * 只有admin才能访问的api在这里实现，可以由用户访问的api都在UserController中实现
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

    /**
     * 搜索user
     *
     * @param page
     * @param limit
     * @param gender
     * @param name
     * @return
     */
    @GetMapping(value = "/user/filter")
    public BaseResult getUserList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                  @RequestParam(value = "gender", required = false) Integer gender,
                                  @RequestParam(value = "name", required = false) String name) {
        BaseResult baseResult = new BaseResult();
        try {

        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.GET_FAILURE);
        }
        return baseResult;
    }

    /**
     * 通过chatId来获取user信息
     *
     * @param chatId
     * @return
     */
    @OpenApi
    @GetMapping(value = "/user/{chatId}")
    public BaseResult getUserInfoById(@PathVariable String chatId) {
        BaseResult baseResult = new BaseResult();
        try {
            UserEntity user = userService.getUserByUsername(chatId);
            baseResult.setStatus(IChatStatus.SUCCESS);
            baseResult.setData(user);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.GET_FAILURE);
        }
        return baseResult;
    }



    /**
     * delete user
     * 只能由管理员才能删除用户
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/user/{id}/delete")
    public BaseResult deleteUser(@PathVariable Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            userService.delete(id);
            baseResult.setStatus(IChatStatus.SUCCESS);
        } catch (Exception e) {
            baseResult.setStatus(IChatStatus.DELETE_FAILURE);
        }
        return baseResult;
    }

    /**
     * 推送message
     *
     * @param msg
     * @return
     */
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
