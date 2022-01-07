package com.itheima.web.controller;


import java.io.Serializable;

/**
 * 页面返回的结果数据封装对象
 */
public class Result implements Serializable {
    //对应操作的返回消息
    private String message;
    //对应操作的返回结果是否成功
    private Boolean flag;
    //对应操作返回的具体数据
    private Object data;
    //对应操作的返回状态码
    private Integer code;

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code = Code.SUCCESS;
        this.flag = true;
    }

    public Result(String message, Boolean flag, Object data, Integer code) {
        this.message = message;
        this.flag = flag;
        this.data = data;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
