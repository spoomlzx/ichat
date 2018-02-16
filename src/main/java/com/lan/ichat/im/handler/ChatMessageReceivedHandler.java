package com.lan.ichat.im.handler;

import com.lan.ichat.service.ChatMessageService;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.MessageHandler;
import org.spoom.im.sdk.server.model.CmdMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.handler
 *
 * @author spoomlan
 * @date 15/02/2018
 */
@Component
public class ChatMessageReceivedHandler implements MessageHandler {
    @Autowired
    private ChatMessageService chatMessageService;

    @Override
    public CmdMessage process(IMSession session, CmdMessage cmdMessage) {
        String msgId = cmdMessage.get("msgId");
        chatMessageService.update(msgId, 1);
        return null;
    }
}
