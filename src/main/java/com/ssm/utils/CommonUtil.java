package com.ssm.utils;

import org.springframework.util.StringUtils;

/**
 *
 * 通用工具类
 *
 * @Author: Think
 * @Date: 2019/3/27
 * @Time: 17:04
 */
public class CommonUtil extends BaseUtil {

    public static boolean notEmpty(Object o) {
        return null != o && !StringUtils.isEmpty(o);
    }


}
