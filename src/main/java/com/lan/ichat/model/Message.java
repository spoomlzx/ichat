package com.lan.ichat.model;

/**
 * package com.lan.ichat.model
 *
 * @author lanzongxiao
 * @date 2017/11/6
 */
public class Message extends org.spoom.im.sdk.server.model.Message{
    /**
     * message 状态
     * 0 已发送
     * 1 已接收
     * 2 已阅读
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
