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
        String CODE_SUCCESS = "200";
        String CODE_FAILURE = "500";
        String CODE_NO_HANDLER = "404";
    }

    interface ProtobufType {
        byte HB_REQUEST = 0;
        byte HB_RESPONSE = 1;
        byte CHAT_MESSAGE = 2;
        byte CMD_MESSAGE = 3;
    }

    interface MessageAction {
        int ACTION_NO_HANDLER = 404;
        int ACTION_FORCE_OFFLINE = 999;
        int ACTION_LOGIN = 901;
        int ACTION_LOGOUT = 902;

        int ACTION_MESSAGE_SEND_SUCCEED = 200;
    }
}
