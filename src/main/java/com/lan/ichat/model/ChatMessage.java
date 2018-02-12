package com.lan.ichat.model;

/**
 * package com.lan.ichat.model
 *
 * @author spoomlan
 * @date 13/02/2018
 */
public class ChatMessage {
    private String msgId;
    // message type 标记消息类型，如：群聊信息、点对点消息
    private Integer chatType;
    // 标记content格式，IMAGE,TEXT,VOICE,VIDEO,FILE,LOCATION;
    private Integer msgType;
    // message 发送者
    private String msgFrom;
    // 接受者
    private String msgTo;
    // 消息内容
    private String body;
    private String extra;
    // 发送的时间戳
    private Long time;

    /*
    0 未接收 1 已接收
    当用户登录时拉取离线消息，获取所有status==0的消息
     */
    private Integer status;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getChatType() {
        return chatType;
    }

    public void setChatType(Integer chatType) {
        this.chatType = chatType;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
