package com.lan.ichat.im.manager;

import com.lan.common.util.StringUtils;
import com.lan.ichat.im.handler.ChatBot;
import com.lan.ichat.service.ChatMessageService;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.*;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.spoom.im.sdk.server.model.CmdMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * package com.lan.ichat.im.manager
 *
 * @author spoomlan
 * @date 18/12/2017
 */
@Component
public class MessageDispatcher implements IDispatcher {
    private final static Logger logger = LoggerFactory.getLogger(MessageDispatcher.class);

    private HashMap<Integer, MessageHandler> handlers = new HashMap<>();
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatBot chatBot;

    @Override
    public void dispatchCmdMessage(Channel channel, CmdMessage message) {
        MessageHandler handler = handlers.get(message.getAction());
        CmdMessage replyMessage;
        IMSession session = new IMSession(channel);
        if (handler != null) {
            replyMessage = handler.process(session, message);
            logger.info("reply: " + replyMessage);
            // 如果session不在sessionManager中，则返回null
            if (replyMessage != null) {
                session.write(replyMessage);
            }
        }
    }

    @Override
    public void dispatchChatMessage(Channel channel, ChatMessage chatMessage) {
        IMSession toSession = sessionManager.get(chatMessage.getMsgTo());
        IMSession session = sessionManager.get((String) channel.attr(AttributeKey.valueOf(IMConstant.SESSION_KEY)).get());
        // 如果session==null，代表是没有绑定的channel，丢弃报文
        if (session != null) {
            chatMessage.setMsgFrom(session.getAccount());
            if (toSession != null) {
                toSession.write(chatMessage);
                logger.info("chat message to transmit： " + chatMessage.toString());
            }
            chatMessageService.insert(chatMessage);

            chatBot.replyChatMessage(chatMessage);

            CmdMessage replyMessage = new CmdMessage();
            replyMessage.setMsgId(StringUtils.getUUID());
            replyMessage.setAction(IMConstant.MessageAction.ACTION_MESSAGE_SEND_SUCCEED);
            replyMessage.setMsgFrom("system");
            replyMessage.put("msgId", chatMessage.getMsgId());
            session.write(replyMessage);
        } else {
            channel.close();
        }
    }

    public MessageHandler get(Integer action) {
        return handlers.get(action);
    }

    public void remove(Integer action) {
        handlers.remove(action);
    }

    public void put(Integer action, MessageHandler handler) {
        if (action != null && handler != null) {
            handlers.put(action, handler);
        }
    }
}
