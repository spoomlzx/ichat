package com.lan.common.util;

/**
 * package com.lan.common.util
 * <p>
 * 200 success
 * 500 failure
 *
 * @author lanzongxiao
 * @date 2017/10/31
 */
public class BaseResult {
    private String msg;
    private int code;
    private Object data;

    public BaseResult() {
        this.code = 200;
    }

    public BaseResult(String msg) {
        this.code = 200;
        this.msg = msg;
    }

    public BaseResult(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    public void setStatus(IChatStatus status) {
        this.code = status.code();
        this.msg = status.getInfo();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
