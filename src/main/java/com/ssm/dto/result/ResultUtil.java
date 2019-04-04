package com.ssm.dto.result;

/**
 * @Author: Think
 * @Date: 2019/4/4
 * @Time: 17:23
 */
public class ResultUtil {

    public static Result success(Object object) {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), "success", object);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.msg, "failed", null);
    }
}
