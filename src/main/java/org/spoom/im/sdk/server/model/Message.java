package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.proto.MessageProto;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.server.model
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class Message implements Protobufable, Serializable {
    private static final long serialVersionUID = 3015312422291883506L;

    // message id
    private long msgId;
    // message type 标记消息类型，如：群聊信息、点对点消息
    private int chatType;
    // 标记content格式，可以为text、json、xml等
    private int format;
    // message 发送者
    private String from;
    // 接受者
    private String to;
    // 消息内容
    private String body;
    private String extra;
    // 发送的时间戳
    private long time;

    public Message() {
        time = System.currentTimeMillis();
    }

    @Override
    public byte[] getByteArray() {
        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
        builder.setMsgId(msgId)
                .setChatType(chatType)
                .setFormat(format)
                .setFrom(from)
                .setTo(to)
                .setBody(body)
                .setExtra(extra)
                .setTime(time);
        return builder.build().toByteArray();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.MESSAGE;
    }

    public String toString() {
        return "#Message#" + "\n" +
                "msgId:" + msgId + "\n" +
                "chatType:" + chatType + "\n" +
                "format:" + format + "\n" +
                "from:" + from + "\n" +
                "to:" + to + "\n" +
                "body:" + body + "\n" +
                "extra:" + extra + "\n" +
                "time:" + time;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
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
