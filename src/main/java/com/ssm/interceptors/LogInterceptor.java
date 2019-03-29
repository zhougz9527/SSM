package com.ssm.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

/**
 * 日志拦截器
 * @Author: Think
 * @Date: 2019/3/26
 * @Time: 15:41
 */
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StarTime");

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(k).append("=");
            if (null != v && v.length == 1) {
                sb.append(v[0]).append("\t");
            } else {
                sb.append(Arrays.toString(v)).append("\t");
            }
        });
        return sb.toString();
    }


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);
        startTimeThreadLocal.set(startTime); //线程绑定变量（该数据只有当前请求的线程可见）
        if (o instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("------开始计时: ").append(new SimpleDateFormat("hh:mm:ss.sss").format(startTime)).append(" ------");
            HandlerMethod handlerMethod = (HandlerMethod) o;
            sb.append("Controller: ").append(handlerMethod.getBean().getClass().getName()).append("\n");
            sb.append("Method: ").append(handlerMethod.getMethod().getName()).append("\n");
            sb.append("Params: ").append(getParamString(httpServletRequest.getParameterMap())).append("\n");
            sb.append("URI: ").append(httpServletRequest.getRequestURI()).append("\n");
            LOGGER.debug(sb.toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (o instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("CostTime: ").append(executeTime).append("ms").append("\n");
            sb.append("-------------");
            LOGGER.debug(sb.toString());
        }

    }

    /**
     * 该方法将在整个请求结束之后, DispatcherServlet渲染对应的视图之后执行, 该方法主要是用户资源清理工作
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 打印JVM信息
        if (LOGGER.isDebugEnabled()) {
            long beginTime = startTimeThreadLocal.get();// 得到线程绑定局部变量的时间
            long endTime = System.currentTimeMillis();
            if (null != e) {
                LOGGER.debug("Controller 异常: {}", e.getMessage());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("计时结束: ").append(new SimpleDateFormat("hh:mm:ss.sss").format(endTime)).append(" 耗时: ").append(endTime - beginTime)
                    .append(" URI: ").append(httpServletRequest.getRequestURI()).append(" 最大内存: ").append(Runtime.getRuntime().maxMemory()/1024/1024).append("m")
                    .append(" 已分配内存: ").append(Runtime.getRuntime().totalMemory()/1024/1024).append("m").append(" 剩余内存: ").append(Runtime.getRuntime().freeMemory()/1024/1024).append("m")
                    .append(" 最大可用内存: ").append((Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024).append("m");
            LOGGER.debug(sb.toString());
            startTimeThreadLocal.remove();
        }
    }
}
