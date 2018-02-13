package com.lan.ichat.im.push;

import com.lan.ichat.model.ChatMessage;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.push
 *
 * @author spoomlan
 * @date 13/02/2018
 */
@Component
public class GroupMessagePusher extends MessagePusher {
    @Override
    public void push(ChatMessage message) {
        super.push(message);
    }
}
