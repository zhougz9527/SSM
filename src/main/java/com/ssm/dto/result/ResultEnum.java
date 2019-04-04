package com.ssm.dto.result;

/**
 * @Author: Think
 * @Date: 2019/4/4
 * @Time: 17:21
 */
public enum ResultEnum {

    SUCCESS(0, "请求成功"),
    PAGE_NOT_FOUND(404, "页面不存在"),
    ACCOUNT_NOT_EXIST(600, "账号不存在");

    public int code;
    public String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
