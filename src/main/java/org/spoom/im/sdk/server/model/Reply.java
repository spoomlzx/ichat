package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.proto.ReplyProto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * package org.spoom.im.sdk.server.model
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public class Reply implements Protobufable, Serializable {
    private static final long serialVersionUID = -1262367179555098383L;

    private int action;
    private int code;
    private String message;
    private long time;
    private HashMap<String, String> data = new HashMap<>();

    @Override
    public byte[] getByteArray() {
        ReplyProto.Reply.Builder builder = ReplyProto.Reply.newBuilder();
        builder.setCode(code);
        if (message != null) {
            builder.setMessage(message);
        }
        if (!data.isEmpty()) {
            builder.putAllData(data);
        }
        builder.setAction(action);
        builder.setTime(time);
        return builder.build().toByteArray();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.REPLY;
    }

    public void put(String k, String v) {
        if (v != null && k != null) {
            data.put(k, v);
        }
    }

    public void putAll(Map<String, String> map) {
        data.putAll(map);
    }

    public String get(String k) {
        return data.get(k);
    }

    public void remove(String k) {
        data.remove(k);
    }

    public Set<String> getKeySet() {
        return data.keySet();
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        buffer.append("#Reply#").append("\n");
        buffer.append("action:").append(action).append("\n");
        buffer.append("time:").append(time).append("\n");
        buffer.append("code:").append(code).append("\n");
        if (!data.isEmpty()) {
            buffer.append("data{").append("\n");
            for (String key : getKeySet()) {
                buffer.append(key).append(":").append(this.get(key)).append("\n");
            }
            buffer.append("}");
        }
        return buffer.toString();
    }
}
