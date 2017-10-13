package com.WorkAttendance.common.security;
/**
 * @Author fangtongle
 * @Date 2017/8/29 18:35
 * @Description
 **/

import com.WorkAttendance.common.utils.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author fangtongle
 * @Date 2017/8/29 18:35
 * @Description
 **/
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        try {
            UsernamePasswordToken unbroken = (UsernamePasswordToken) token;
            String password = String.valueOf(unbroken.getPassword());
            Object tokenCredentials = MD5Utils.encryptPassword(password);
            Object accountCredentials = getCredentials(info);
            return equals(tokenCredentials, accountCredentials);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
