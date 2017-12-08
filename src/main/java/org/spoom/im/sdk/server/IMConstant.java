package org.spoom.im.sdk.server;

/**
 * package org.spoom.im.sdk.android
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public interface IMConstant {
    long CONNECT_INTERVAL_TIME = 30 * 1000;
    int HEADER_LENGTH = 3; // 使用3byte来保存header
    String SESSION_KEY = "account";
    String KEY_HEARTBEAT = "heartbeat";
    String CLIENT_HEARTBEAT = "client_heartbeat";

    interface ReturnCode {
        int CODE_200 = 200;
    }

    interface ProtobufType {
        byte HB_REQUEST = 0;
        byte HB_RESPONSE = 1;
        byte MESSAGE = 2;
        byte CALL_MESSAGE = 3;
        byte REPLY = 4;
    }

    interface Protobuf {
        String HB_REQUEST_NAME = "SERVER_HEARTBEAT_REQUEST";
        String HB_RESPONSE_NAME = "SERVER_HEARTBEAT_RESPONSE";
        String HB_REQUEST_BODY = "REQ";
        String HB_RESPONSE_BODY = "RES";
    }

    interface HandlerType {
        int BIND_CLIENT = 0;
        int CLOSE_SESSION = 1;
    }

    interface MessageAction {
        int ACTION_FORCEOFFLINE = 999;
    }
}
