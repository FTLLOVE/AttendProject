package com.WorkAttendance.attend.dao;

import com.WorkAttendance.attend.entity.Attend;
import com.WorkAttendance.attend.entity.AttendExample;

import java.util.List;

import com.WorkAttendance.attend.vo.QueryCondition;
import org.apache.ibatis.annotations.Param;

public interface AttendMapper {
    int countByExample(AttendExample example);

    int deleteByExample(AttendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    List<Attend> selectByExample(AttendExample example);

    Attend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByExample(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);

    int countByCondition(QueryCondition condition);

    List<Attend> selectAttendPage(QueryCondition condition);

    List<Long> selectTodayAbsence();

    void batchInsert(List<Attend> attendList);

    List<Attend> selectTodayEveningAbsence();
}