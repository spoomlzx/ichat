package com.lan.ichat.im.push;

import com.lan.ichat.model.ChatMessage;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.push
 *
 * @author lanzongxiao
 * @date 2017/11/4
 */
@Component
public class DefaultMessagePusher extends MessagePusher {

    @Override
    public void push(ChatMessage msg) {
        if (msg.getMsgFrom() == null) {
            msg.setMsgFrom("system");
        }
        super.push(msg);
        this.messageService.insert(msg);
    }
}
