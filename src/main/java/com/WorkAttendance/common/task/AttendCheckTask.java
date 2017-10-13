package com.WorkAttendance.common.task;

import com.WorkAttendance.attend.service.AttendService;

import javax.annotation.Resource;

/**
 * @Author fangtongle
 * @Date 2017/8/24 14:39
 * @Description Quartz定时任务
 **/
public class AttendCheckTask {

    @Resource
    private AttendService attendService;

    public void checkAttend() {
        attendService.checkAttend();
        System.out.println("定时器执行了......");
    }
}
