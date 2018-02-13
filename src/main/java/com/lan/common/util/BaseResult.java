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

    public BaseResult setStatus(IChatStatus status) {
        this.code = status.code();
        this.msg = status.getInfo();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public BaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BaseResult setData(Object data) {
        this.data = data;
        return this;
    }
}
