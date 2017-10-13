package com.WorkAttendance.login.controller;


import com.WorkAttendance.user.entity.User;
import com.WorkAttendance.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(value = "login")
public class LoginController {


    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping()
    public String login() {
        return "login";
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/18 15:57
     * @Description 校验登陆
     **/
    @RequestMapping("/check")
    @ResponseBody
    public String checkName(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            SecurityUtils.getSubject().getSession().setTimeout(1800000);
        } catch (Exception e) {
            return "loginFail";
        }
        return "login_Success";

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        // 查询数据库，如果返回是true就进行MD5比对处理
//        User user = userService.findUserByUserName(username);
//
        // 这边使用双重校验，1. 判断用户是否存在 2. 判断密码是否一致
//        if (user != null) {
//            boolean checkPassword = MD5Utils.checkPassword(password, user.getPassword());
//            if (checkPassword) {
//                request.getSession().setAttribute("userInfo", user);
//                return "loginSuccess";
//            } else {
//                return "loginFail";
//            }
//        } else {
//            return "loginFail";
//        }
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/18 16:04
     * @Description
     **/
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.createUser(user);
        return "suc";

    }

}
