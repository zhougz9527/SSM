package com.ssm.dto.result;

/**
 * @Author: Think
 * @Date: 2019/3/19
 * @Time: 18:08
 */
public class Result {
    private int code;
    private String msg;
    private String status;
    private Object result;

    Result(int code, String msg, String status, Object result) {
        this.code = code;
        this.msg = msg;
        this.status = status;
        this.result = result;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
