package com.WorkAttendance.common.utils;

/***
 * 这边主要使用的是MD5加密的方式来对密码的加密和密码的比对判断是否一致
 */

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * @Author fangtongle
     * @Date 2017/8/19 10:25
     * @Description 这边主要是将密码加密
     **/
    public static String encryptPassword(String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(md5.digest(password.getBytes("UTF-8")));
        return result;
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/19 10:25
     * @Description 判断两次密码是否一致
     **/
    public static boolean checkPassword(String inputpwd, String dbpwd) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        String result = encryptPassword(inputpwd);
        if (result.equals(dbpwd)) {
            return true;
        } else {
            return false;
        }
    }
}
