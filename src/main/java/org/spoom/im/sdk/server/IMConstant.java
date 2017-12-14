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
        int CODE_404 = 400;
    }

    interface ProtobufType {
        byte HB_REQUEST = 0;
        byte HB_RESPONSE = 1;
        byte CHAT_MESSAGE = 2;
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

    interface ChatType {
        int CHAT_SINGLE = 0;
        int CHAT_GROUP = 1;
        int CHAT_PUBLIC = 2;
    }

    interface MessageType {
        int MSG_TEXT = 0;
        int MSG_IMAGE = 1;
        int MSG_VOICE = 2;
        int MSG_VIDEO = 3;
        int MSG_LOCATION = 4;
        int MSG_FILE = 5;
    }
}
