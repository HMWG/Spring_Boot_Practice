package com.grepp.spring_practice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer loginNo = (Integer) request.getSession().getAttribute("loginNo");
        if(loginNo == null){
            request.setAttribute("msg","먼저 로그인을 해주세요");
            response.sendRedirect(request.getContextPath()+"/user/login");
            return false;
        }
        return true;
    }
}
