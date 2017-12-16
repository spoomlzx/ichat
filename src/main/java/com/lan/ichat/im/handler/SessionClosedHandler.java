package com.lan.ichat.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.MessageHandler;
import org.spoom.im.sdk.server.SessionManager;
import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.handler
 *
 * @author lanzongxiao
 * @date 2017/11/4
 */
@Component
public class SessionClosedHandler implements MessageHandler {

    protected final Logger logger = LoggerFactory.getLogger(SessionClosedHandler.class);

    @Autowired
    private SessionManager sessionManager;

    @Override
    public Reply process(IMSession session, CallMessage callMessage) {
        logger.info("client session closed handler");
        Object account = session.getAttribute(IMConstant.SESSION_KEY);
        if (account == null) {
            return null;
        }
        // 删除channel上绑定的account
        session.removeAttribute(IMConstant.SESSION_KEY);
        IMSession curSession = sessionManager.get(account.toString());
        // 如果id不同，则代表断开的不是当前account绑定的channel，不用删除session
        if (curSession.getId().equals(session.getId())) {
            sessionManager.remove(account.toString());
        }
        logger.info("session removed, id: " + session.getId());
        return null;
    }
}
