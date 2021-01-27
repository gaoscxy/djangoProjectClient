package com.gaos.book.base;

import java.io.Serializable;

/**
 * Created by gaos on 2017/8/4.
 */


public class BaseBean<Result> implements Serializable{

    private int code;
    private String msg;
    private Result data;

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

    public Result getData() {
        return data;
    }

    public void setData(Result data) {
        this.data = data;
    }

}
