package com.WorkAttendance.common.interceptor;

import com.WorkAttendance.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        if ((uri.indexOf("login") >= 0) || (uri.indexOf("sign") >= 0) || (uri.indexOf("error") >= 0) || (uri.indexOf("home") >= 0)) {
            return true;
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
//        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            return true;
        }
        //转发到登录
        request.getRequestDispatcher("/login").forward(request, response);
        return false;

//        String requestURI = request.getRequestURI();
//        if ((requestURI.indexOf("login") >= 0) || (requestURI.indexOf("sign") >= 0) || (requestURI.indexOf("attend")
//                >= 0) || requestURI.indexOf("start") >= 0 || requestURI.indexOf("list") >= 0 || requestURI.indexOf("approve") >= 0) {
//            return true;
//        }
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("userInfo");
//        if (user != null) {
//            return true;
//        }
//        request.getRequestDispatcher("/login/login1").forward(request, response);
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
