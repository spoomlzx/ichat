package com.lan.ichat.controller;

import com.lan.common.annotation.LoginUser;
import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.common.util.StringUtils;
import com.lan.ichat.im.push.PusherManager;
import com.lan.ichat.model.ChatMessage;
import com.lan.ichat.model.UserEntity;
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

    /**
     * 推送message
     *
     * @param message
     * @return
     */
    @PostMapping(value = "/send")
    public BaseResult send(@RequestBody ChatMessage message, @LoginUser UserEntity user) {
        BaseResult baseResult = new BaseResult();
        try {
            // 13/02/2018 TODO 这里应该进行msgTo是否是user的好友的判断，初步思路是基于redis的set实现
            if (StringUtils.isEmpty(message.getMsgTo())) {
                throw new IllegalArgumentException("msgTo can not be null");
            }
            if (!message.getMsgFrom().equals(user.getUsername())) {
                throw new IllegalArgumentException("message from invalid user");
            }
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
    @GetMapping(value = "/offline")
    public BaseResult offlineMessage(@LoginUser UserEntity user) {
        BaseResult baseResult = new BaseResult();
        return baseResult;
    }
}
