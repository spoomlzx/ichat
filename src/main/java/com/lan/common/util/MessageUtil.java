package com.lan.common.util;

import org.spoom.im.sdk.server.model.ChatMessage;

/**
 * package com.lan.common.util
 *
 * @author spoomlan
 * @date 13/02/2018
 */
public class MessageUtil {

    public static ChatMessage transform(com.lan.ichat.model.ChatMessage msg) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgId(msg.getMsgId());
        chatMessage.setChatType(msg.getChatType());
        chatMessage.setMsgType(msg.getMsgType());
        chatMessage.setMsgFrom(msg.getMsgFrom());
        chatMessage.setMsgTo(msg.getMsgTo());
        chatMessage.setBody(msg.getBody());
        chatMessage.setTime(msg.getTime());
        chatMessage.setExtra(msg.getExtra());
        chatMessage.setStatus(msg.getStatus());
        return chatMessage;
    }

    public static com.lan.ichat.model.ChatMessage clone(com.lan.ichat.model.ChatMessage msg) {
        com.lan.ichat.model.ChatMessage chatMessage = new com.lan.ichat.model.ChatMessage();
        chatMessage.setMsgId(msg.getMsgId());
        chatMessage.setChatType(msg.getChatType());
        chatMessage.setMsgType(msg.getMsgType());
        chatMessage.setMsgFrom(msg.getMsgFrom());
        chatMessage.setMsgTo(msg.getMsgTo());
        chatMessage.setBody(msg.getBody());
        chatMessage.setTime(msg.getTime());
        chatMessage.setExtra(msg.getExtra());
        chatMessage.setStatus(msg.getStatus());
        return chatMessage;
    }
}
