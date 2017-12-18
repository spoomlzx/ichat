package com.lan.ichat.im.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.*;
import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.spoom.im.sdk.server.model.Reply;
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

    @Override
    public void dispatchCallMessage(IMSession session, CallMessage message) {
        MessageHandler handler = handlers.get(message.getAction());
        if (handler == null) {
            Reply reply = new Reply();
            reply.setAction(message.getAction());
            reply.setCode(IMConstant.ReturnCode.CODE_404);
            reply.setMessage("No handler");
            //session.write(reply);
        } else {
            Reply reply = handler.process(session, message);
            if (reply != null) {
                session.write(reply);
            }
        }
    }

    @Override
    public void dispatchChatMessage(IMSession session, ChatMessage chatMessage) {
        IMSession toSession = sessionManager.get(chatMessage.getTo());
        if (session != null) {
            chatMessage.setFrom(session.getAccount());
            toSession.write(chatMessage);
            logger.info("chat message to transmit： " + chatMessage.toString());
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
