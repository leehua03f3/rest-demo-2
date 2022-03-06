package com.example.antraassignment.exceptions;

public class ErrorResponse {
    private String msg;
    private Integer code;
    private Exception data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Exception getData() {
        return data;
    }

    public void setData(Exception data) {
        this.data = data;
    }
}
