package com.cc.behaviordetectionbackend.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName CustomInterceptor
 * @Description 拦截器
 * @Author cc
 * @Date 2025/11/29 12:28
 * @Version 1.0.0
 */
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        response.setHeader( "Set-Cookie" , "cookiename=httponlyTest;Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly");
//        response.setHeader( "Content-Security-Policy" , "default-src 'self'; script-src 'self'; frame-ancestors 'self'");
//        response.setHeader("Access-Control-Allow-Origin", (request).getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Referrer-Policy","no-referrer");
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
        long tokenTimeout = StpUtil.getTokenTimeout();// 获取过期时间
        //token没过期，过期时间不是-1的时候，每次请求都刷新过期时间
        if (tokenTimeout != -1){
            StpUtil.renewTimeout(3600);// 用于token续期
            StpUtil.updateLastActiveToNow();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
