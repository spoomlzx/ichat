package com.lan.ichat.im.push;

import com.farsunset.cim.sdk.server.session.CIMSession;
import com.farsunset.cim.sdk.server.session.SessionManager;
import com.lan.ichat.model.Message;
import com.lan.ichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.push
 *
 * @author lanzongxiao
 * @date 2017/11/6
 */
@Component
public class MessagePusher {
    @Autowired
    protected SessionManager sessionManager;
    @Autowired
    protected UserService userService;

    public void push(Message msg) {
        CIMSession session = sessionManager.get(msg.getReceiver());
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
    public void push(Message msg, CIMSession session) {
        if (session != null && session.isConnected()) {
            session.write(msg);
        }
    }
}
