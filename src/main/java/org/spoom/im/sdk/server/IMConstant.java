package org.spoom.im.sdk.server;

/**
 * package org.spoom.im.sdk.android
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public interface IMConstant {
    String KEY_IM_SERVER_HOST = "KEY_IM_SERVER_HOST";
    String KEY_IM_SERVER_PORT = "KEY_IM_SERVER_PORT";

    long CONNECT_INTERVAL_TIME = 30 * 1000;
    int HEADER_LENGTH = 3; // 使用3byte来保存header

    interface ReturnCode {
        int CODE_200 = 200;
    }

    interface ProtobufType {
        byte HB_REQUEST = 0;
        byte HB_RESPONSE = 1;
        byte MESSAGE = 2;
        byte REQUESTMSG = 3;
        byte RESPONSEMSG = 4;
    }

    interface Protobuf {
        String HB_REQUEST_NAME = "SERVER_HEARTBEAT_REQUEST";
        String HB_RESPONSE_NAME = "SERVER_HEARTBEAT_RESPONSE";
        String HB_REQUEST_BODY = "REQ";
        String HB_RESPONSE_BODY = "RES";
    }

    interface RequestKey {
        String CLIENT_BIND = "CLIENT_BIND";

    }

    interface MessageAction {
        int ACTION_FORCEOFFLINE = 999;
    }

    interface ServiceType {
        int TYPE_INIT = 0;
        int TYPE_LOGIN = 1;
        int TYPE_LOGOUT = 2;
        int TYPE_CHAT = 3;
        int TYPE_CHAT_CMD = 4;
        int TYPE_AWAKE = 5;
    }
}
