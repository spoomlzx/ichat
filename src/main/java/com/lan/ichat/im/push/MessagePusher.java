package com.lan.ichat.im.push;

import com.lan.common.util.MessageUtil;
import com.lan.ichat.service.ChatMessageService;
import com.lan.ichat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.SessionManager;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.push
 *
 * @author lanzongxiao
 * @date 2017/11/6
 */
@Component
public class MessagePusher {
    private final static Logger logger = LoggerFactory.getLogger(MessagePusher.class);

    @Autowired
    protected SessionManager sessionManager;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ChatMessageService messageService;

    @Async
    public void push(ChatMessage msg) {
        IMSession session = sessionManager.get(msg.getMsgTo());
        logger.info("thread name: " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId());
        this.push(msg, session);
    }

    /**
     * msg中必须有以下参数，才能wirte
     * receiver
     * sender
     * content
     * action
     * mid
     *
     * @param msg
     * @param session
     */
    @Async
    public void push(ChatMessage msg, IMSession session) {
        if (session != null && session.isConnected()) {
            session.write(msg);
            // 05/12/2017 TODO save this msg to database
            logger.info("send finish");
        }
    }

    public void push(com.lan.ichat.model.ChatMessage message) {
        this.push(MessageUtil.transform(message));
    }
}
