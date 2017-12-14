package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.proto.ChatMessageProto;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.server.model
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class ChatMessage implements Protobufable, Serializable {
    private static final long serialVersionUID = 3015312422291883506L;

    // message id
    private String msgId;
    // message type 标记消息类型，如：群聊信息、点对点消息
    private int chatType;
    // 标记content格式，IMAGE,TEXT,VOICE,VIDEO,FILE,LOCATION;
    private int msgType;
    // message 发送者
    private String from;
    // 接受者
    private String to;
    // 消息内容
    private String body;
    private String extra;
    // 发送的时间戳
    private long time;

    public ChatMessage() {
        time = System.currentTimeMillis();
    }

    @Override
    public byte[] getByteArray() {
        ChatMessageProto.ChatMessage.Builder builder = ChatMessageProto.ChatMessage.newBuilder();
        builder.setMsgId(msgId)
                .setChatType(chatType)
                .setMsgType(msgType)
                .setFrom(from)
                .setTo(to)
                .setBody(body)
                .setExtra(extra)
                .setTime(time);
        return builder.build().toByteArray();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.CHAT_MESSAGE;
    }

    public String toString() {
        return "#ChatMessage#" + "\n" +
                "msgId:" + msgId + "\n" +
                "chatType:" + chatType + "\n" +
                "msgType:" + msgType + "\n" +
                "from:" + from + "\n" +
                "to:" + to + "\n" +
                "body:" + body + "\n" +
                "extra:" + extra + "\n" +
                "time:" + time;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
