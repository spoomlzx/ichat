package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.proto.CallMessageProto;

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

public class CallMessage implements Protobufable, Serializable {
    private static final long serialVersionUID = 42546118754185918L;

    private int callType;
    private long time;
    private HashMap<String, String> data = new HashMap<>();

    @Override
    public byte[] getByteArray() {
        CallMessageProto.CallMessage.Builder builder = CallMessageProto.CallMessage.newBuilder();
        builder.setCallType(callType)
                .setTime(time);
        if (!data.isEmpty()) {
            builder.putAllData(data);
        }
        return builder.build().toByteArray();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.CALL_MESSAGE;
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

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
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
        buffer.append("#CallMessage#").append("\n");
        buffer.append("callType:").append(this.getCallType()).append("\n");
        buffer.append("time:").append(time).append("\n");
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
