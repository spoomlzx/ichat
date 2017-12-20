package com.lan.ichat.im.handler;

import com.lan.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.MessageHandler;
import org.spoom.im.sdk.server.SessionManager;
import org.spoom.im.sdk.server.model.CmdMessage;
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
    public CmdMessage process(IMSession session, CmdMessage callMessage) {
        logger.info("client session closed handler");
        CmdMessage replyMessage = new CmdMessage();
        replyMessage.setMsgId(StringUtils.getUUID());
        replyMessage.setAction(IMConstant.MessageAction.ACTION_LOGOUT);
        replyMessage.setTo(session.getAccount());
        replyMessage.setTime(System.currentTimeMillis());
        replyMessage.put("code", IMConstant.ReturnCode.CODE_FAILURE);
        Object account = session.getAttribute(IMConstant.SESSION_KEY);
        if (account == null) {
            return null;
        }
        // 如果id不同，则代表断开的不是当前account绑定的channel，不用删除session
        if (callMessage.getFrom() != null && callMessage.getFrom().equals(session.getAccount())) {
            // 删除channel上绑定的account
            session.removeAttribute(IMConstant.SESSION_KEY);
            sessionManager.remove(account.toString());
            replyMessage.put("code", IMConstant.ReturnCode.CODE_SUCCESS);
        }
        logger.info("session removed, id: " + session.getId());
        return replyMessage;
    }
}
