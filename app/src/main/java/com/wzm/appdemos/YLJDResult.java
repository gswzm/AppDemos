package com.wzm.appdemos;

import java.io.Serializable;

/**
 * Created by wangzm on 2017/8/7.
 */

public class YLJDResult<T> implements Serializable {
    private String msg;
    private int code ;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
