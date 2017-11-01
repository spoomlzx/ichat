package com.lan.common.exception;

import com.lan.common.util.IChatStatus;

/**
 * package com.lan.common.exception
 * 自定义Exception
 * 在Exception中加入code,msg参数，传递给ExceptionHandler处理
 *
 * @author lanzongxiao
 * @date 2017/10/31
 */
public class IChatException extends RuntimeException {

    private String msg = IChatStatus.FAILURE.getReasonPhrase();
    private int code = IChatStatus.FAILURE.value();

    public IChatException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public IChatException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public IChatException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public IChatException(IChatStatus status) {
        super(status.getReasonPhrase());
        this.msg = status.getReasonPhrase();
        this.code = status.value();
    }

    public IChatException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
