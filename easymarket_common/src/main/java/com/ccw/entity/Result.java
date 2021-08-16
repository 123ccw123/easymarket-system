package com.ccw.entity;

public class Result<T> {
    private boolean flag;//返回判断
    private Integer code;//返回码
    private String message;//返回消息
    private T data;//返回数据


    public Result() {
        this.flag = true;

        this.code = StatusCode.OK;
        this.message = "操作成功";
    }

    public Result(boolean flag, Integer code,String message ){
        this.message = message;
        this.code = code;
        this.flag = flag;
    }

    public Result(boolean flag, Integer code,String message,  T data) {
        this.message = message;
        this.code = code;
        this.flag = flag;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
