package com.lucky.domain;

/**
 * Created by lucky on 3/1/17.
 */
public class ResponseObj<T> {
    public final static int OK = 1, FAILED = 0, EMPTY = -1;
    public final static String OK_STR = "success", FAILED_STR = "failed", EMPTY_STR = "empty";

    private int code;    // 状态码：成功 1；失败 0；空数据 －1
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
