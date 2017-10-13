package com.WorkAttendance.user.dao;
/**
 * @Author fangtongle
 * @Date 2017/8/19 21:59
 * @Description
 **/

import com.WorkAttendance.user.entity.User;
import com.WorkAttendance.user.entity.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User user);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名来查询用户
     *
     * @param userName
     * @return
     */
    User selectByUserName(String userName);
}