package org.spoom.im.sdk.server.model;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.android.model
 *
 * @author lanzongxiao
 * @date 05/12/2017
 */

public class ResponseMsg implements Serializable {
    private static final long serialVersionUID = -6766023928293695711L;

    private int chatType;
    private int code;
    private String body;
    private long time;

    @Override
    public String toString() {
        return "#ResponseMsg#: {" + "\n" +
                "chatType:" + chatType + "\n" +
                "code:" + code + "\n" +
                "body:" + body + "\n" +
                "time:" + time + "\n}";
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
