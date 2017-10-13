package com.WorkAttendance.user.controller;
/**
 * @Author fangtongle
 * @Date 2017/8/19 21:59
 * @Description
 **/

import com.WorkAttendance.user.entity.User;
import com.WorkAttendance.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/home")
    public String goHome() {
        return "home";
    }

    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        return user;
    }

    @RequestMapping(value = "exit")
    public String userExit(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
