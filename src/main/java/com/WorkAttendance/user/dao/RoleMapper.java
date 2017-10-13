package com.WorkAttendance.user.dao;


import com.WorkAttendance.user.entity.Role;

public interface RoleMapper {

    int deleteByPrimaryKey(Long roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}