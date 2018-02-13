package com.lan.ichat.im.push;

import com.lan.ichat.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * package com.lan.ichat.im.push
 *
 * @author spoomlan
 * @date 13/02/2018
 */
@Component
public class PusherManager {
    @Autowired
    private DefaultMessagePusher defaultMessagePusher;
    @Autowired
    private GroupMessagePusher groupMessagePusher;
    @Autowired
    private PublicAccountMessagePusher publicAccountMessagePusher;

    public void push(ChatMessage message) {
        if (0 == message.getChatType()) {
            defaultMessagePusher.push(message);
        } else if (1 == message.getChatType()) {
            groupMessagePusher.push(message);
        } else if (2 == message.getChatType()) {
            publicAccountMessagePusher.push(message);
        }
    }
}
