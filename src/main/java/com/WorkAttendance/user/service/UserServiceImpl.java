package com.WorkAttendance.user.service;


import com.WorkAttendance.common.utils.MD5Utils;
import com.WorkAttendance.user.dao.UserMapper;
import com.WorkAttendance.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Transactional
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * @Author fangtongle
     * @Date 2017/8/18 15:56
     * @Description 根据用户名来查询用户
     **/
    @Override
    public User findUserByUserName(String userName) {
        User user = null;
        try {
            user = userMapper.selectByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/18 16:06
     * @Description 注册用户, 并且将密码加密保存
     **/
    @Override
    public void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword(MD5Utils.encryptPassword(user.getPassword()));
        userMapper.insertSelective(user);
//        String password = MD5Utils.encryptPassword(user.getPassword());
//        user.setPassword(password);
//        userMapper.insertSelective(user);
    }
}
