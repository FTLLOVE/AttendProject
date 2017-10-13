package com.WorkAttendance.user.service;

import com.WorkAttendance.user.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {

    User findUserByUserName(String userName);

    void createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

}
