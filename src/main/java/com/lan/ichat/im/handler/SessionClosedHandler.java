package com.lan.ichat.im.handler;

import com.farsunset.cim.sdk.server.constant.CIMConstant;
import com.farsunset.cim.sdk.server.handler.CIMRequestHandler;
import com.farsunset.cim.sdk.server.model.ReplyBody;
import com.farsunset.cim.sdk.server.model.SentBody;
import com.farsunset.cim.sdk.server.session.CIMSession;
import com.farsunset.cim.sdk.server.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.handler
 *
 * @author lanzongxiao
 * @date 2017/11/4
 */
@Component
public class SessionClosedHandler implements CIMRequestHandler {

    protected final Logger logger = LoggerFactory.getLogger(SessionClosedHandler.class);

    @Autowired
    private SessionManager sessionManager;
    
    @Override
    public ReplyBody process(CIMSession session, SentBody message) {
        logger.info("client session closed handler");
        Object account = session.getAttribute(CIMConstant.SESSION_KEY);
        if (account == null) {
            return null;
        }
        session.removeAttribute(CIMConstant.SESSION_KEY);
        sessionManager.remove(account.toString());
        return null;
    }
}
