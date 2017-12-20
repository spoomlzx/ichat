package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.proto.CmdMessageProto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * package org.spoom.im.sdk.server.model
 * 发往server的Request，进行编码
 * implements Protobufable
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class CmdMessage implements Protobufable, Serializable {
    private static final long serialVersionUID = 42546118754185918L;

    private String msgId;
    private int chatType;
    private int action;
    private String from;
    private String to;
    private long time;
    private HashMap<String, String> data = new HashMap<>();

    public CmdMessage() {
        chatType = ChatType.CHAT_SINGLE; // 默认为0，singleChat
        time = System.currentTimeMillis();
    }

    @Override
    public byte[] getByteArray() {
        CmdMessageProto.CmdMessage.Builder builder = CmdMessageProto.CmdMessage.newBuilder();
        builder.setMsgId(msgId)
                .setChatType(chatType)
                .setAction(action)
                .setFrom(from)
                .setTo(to)
                .setTime(time);
        if (!data.isEmpty()) {
            builder.putAllData(data);
        }
        return builder.build().toByteArray();
    }

    interface ChatType {
        int CHAT_SINGLE = 0;
        int CHAT_GROUP = 1;
        int CHAT_PUBLIC = 2;
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.CMD_MESSAGE;
    }

    public String get(String k) {
        return data.get(k);
    }

    public void remove(String k) {
        data.remove(k);
    }

    public void put(String k, String v) {
        if (v != null && k != null) {
            data.put(k, v);
        }
    }

    public void putAll(Map<String, String> map) {
        data.putAll(map);
    }

    public Set<String> getKeySet() {
        return data.keySet();
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

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
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

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("#CmdMessage#").append("\n");
        buffer.append("msgId: ").append(msgId).append("\n");
        buffer.append("chatType: ").append(chatType).append("\n");
        buffer.append("from: ").append(from).append("\n");
        buffer.append("to: ").append(to).append("\n");
        buffer.append("time: ").append(time).append("\n");
        if (!data.isEmpty()) {
            buffer.append("data{").append("\n");
            for (String key : getKeySet()) {
                buffer.append(key).append(": ").append(this.get(key)).append("\n");
            }
            buffer.append("}");
        }
        return buffer.toString();
    }
}
