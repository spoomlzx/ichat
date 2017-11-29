package com.lan.ichat.im.push;

import com.farsunset.cim.sdk.server.session.CIMSession;
import com.farsunset.cim.sdk.server.session.SessionManager;
import com.lan.ichat.model.Message;
import com.lan.ichat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Async
    public void push(Message msg) {
        CIMSession session = sessionManager.get(msg.getReceiver());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void push(Message msg, CIMSession session) {
        if (session != null && session.isConnected()) {
            session.write(msg);
            logger.info("send finish");
        }
    }
}
