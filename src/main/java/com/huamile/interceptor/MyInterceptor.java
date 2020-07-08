package com.huamile.interceptor;


import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Afa
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginEmp = session.getAttribute("loginEmp");
        String uri = request.getRequestURI();
        if (uri.contains("toLogin") || uri.contains("toRegister") || uri.contains("/css/")
        || uri.contains("/fonts/") || uri.contains("/images/") || uri.contains("/js/")
        || uri.contains("/layui/") || uri.contains("/vendor/") || uri.contains("/404style/")
        || uri.contains("changeLanguage") || uri.contains("login")){
            return true;
        }else {
            if (loginEmp != null){
                return true;
            }else {
                response.sendRedirect(request.getContextPath()+"/employeesController/toLogin");
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
