package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.android.IMConstant;
import org.spoom.im.sdk.android.model.proto.RequestMsgProto;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.android.model
 * 发往server的Request，进行编码
 * implements Protobufable
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class RequestMsg implements Protobufable, Serializable {
    private static final long serialVersionUID = 42546118754185918L;

    private long msgId;
    private int chatType;
    private String body;
    private String from;
    private String to;
    private long time;

    @Override
    public byte[] getByteArray() {
        RequestMsgProto.RequestMsg.Builder builder = RequestMsgProto.RequestMsg.newBuilder();
        builder.setMsgId(msgId)
                .setChatType(chatType)
                .setBody(body)
                .setFrom(from)
                .setTo(to)
                .setTime(time);

        return builder.build().toByteArray();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.REQUESTMSG;
    }

    @Override
    public String toString() {
        return "#RequestMsg#: {" + "\n" +
                "msgId:" + msgId + "\n" +
                "chatType:" + chatType + "\n" +
                "body:" + body + "\n" +
                "from:" + from + "\n" +
                "to:" + to + "\n" +
                "time:" + time + "\n}";
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
