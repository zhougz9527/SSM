package com.ssm.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 登录拦截器
 *
 * @Author: Think
 * @Date: 2019/3/26
 * @Time: 15:07
 */
public class LoginInterceptor extends BaseInterceptor implements HandlerInterceptor {

    /**
     * 在业务处理器处理业务之前被调用, 当返回true则往后执行, 返回false则不往后执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("进入 preHandle 方法... requestUrl: {}, requestUri: {}", httpServletRequest.getRequestURL(), httpServletRequest.getRequestURI());
        return true;
    }

    /**
     * 在业务处理器业务处理完成后, 生成视图前被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.debug("进入 postHandle 方法... requestUrl: {}, requestUri: {}", httpServletRequest.getRequestURL(), httpServletRequest.getRequestURI());
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.debug("进入 afterCompletion 方法... requestUrl: {}, requestUri: {}", httpServletRequest.getRequestURL(), httpServletRequest.getRequestURI());

    }
}
