package com.WorkAttendance.attend.service;


import com.WorkAttendance.attend.entity.Attend;
import com.WorkAttendance.attend.vo.QueryCondition;
import com.WorkAttendance.common.page.PageQueryBean;

public interface AttendService {

    void signAttend(Attend attend) throws Exception;

    PageQueryBean listAttend(QueryCondition condition);

    void checkAttend();
}
