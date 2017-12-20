package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.server.IMConstant;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.server.model
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class HeartbeatRequest implements Protobufable, Serializable {
    private static final long serialVersionUID = -7448882163108173887L;

    private static final String name = "REQ";

    private static final HeartbeatRequest request = new HeartbeatRequest();

    private HeartbeatRequest() {
    }

    public static HeartbeatRequest getInstance() {
        return request;
    }

    @Override
    public byte[] getByteArray() {
        return name.getBytes();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.HB_REQUEST;
    }

    @Override
    public String toString() {
        return name;
    }
}
