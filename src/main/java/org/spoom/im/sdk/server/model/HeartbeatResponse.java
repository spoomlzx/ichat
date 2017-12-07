package org.spoom.im.sdk.server.model;

import org.spoom.im.sdk.android.IMConstant;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.android.model
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class HeartbeatResponse implements Protobufable, Serializable {
    private static final long serialVersionUID = -4594722878777079531L;

    private static final HeartbeatResponse response = new HeartbeatResponse();

    private HeartbeatResponse() {
    }

    public static HeartbeatResponse getInstance() {
        return response;
    }

    @Override
    public byte[] getByteArray() {
        return IMConstant.Protobuf.HB_RESPONSE_BODY.getBytes();
    }

    @Override
    public byte getType() {
        return IMConstant.ProtobufType.HB_RESPONSE;
    }

    @Override
    public String toString() {
        return IMConstant.Protobuf.HB_RESPONSE_NAME;
    }
}
