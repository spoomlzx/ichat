package com.lan.ichat.controller;

import com.lan.common.annotation.LoginUser;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.common.util.StringUtils;
import com.lan.ichat.im.push.PusherManager;
import com.lan.ichat.model.ChatMessage;
import com.lan.ichat.model.User;
import com.lan.ichat.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * package com.lan.ichat.controller
 *
 * @author spoomlan
 * @date 13/02/2018
 */
@RestController
@RequestMapping(value = "/api/message")
public class MessageController {
    private final static Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private PusherManager pusherManager;
    @Autowired
    private TokenService tokenService;

    /**
     * 推送message
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/send")
    public BaseResult send(@RequestBody ChatMessage message, @LoginUser User user) {
        BaseResult baseResult = new BaseResult();
        try {
            if (StringUtils.isEmpty(message.getMsgTo()) || !tokenService.isFriend(user.getUsername(), message.getMsgTo())) {
                throw new IllegalArgumentException("msgTo valid");
            }
            message.setMsgFrom(user.getUsername());
            pusherManager.push(message);
            baseResult.setMsg("Message send success");
            Map<String, Object> data = new HashMap<>();
            data.put("msgId", message.getMsgId());
            data.put("time", message.getTime());
            baseResult.setData(data);
        } catch (Exception e) {
            baseResult.setCode(IChatStatus.FAILURE.code());
            baseResult.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return baseResult;
    }

    /**
     * 撤回一条消息
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/withdraw")
    public BaseResult withdraw(@RequestBody ChatMessage message) {
        BaseResult baseResult = new BaseResult();
        return baseResult;
    }

    /**
     * 用于用户登录时，拉取所有没有收到的离线消息
     *
     * @param user 当前登录用户
     * @return
     */
    @GetMapping(value = "/pullOffline")
    public BaseResult offlineMessage(@LoginUser User user) {
        BaseResult baseResult = new BaseResult();
        return baseResult;
    }
}
