package com.WorkAttendance.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author fangtongle
 * @Date 2017/8/21 11:01
 * @Description 处理打卡的时间
 **/
public class DateUtils {

    private static Calendar calendar = Calendar.getInstance();

    /**
     * @Author fangtongle
     * @Date 2017/8/21 10:45
     * @Description 获取今天是星期几
     **/
    public static int getWeek() {
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0) {
            week = 7;
        }
        return week;
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/21 10:45
     * @Description 获取打卡的时间差
     **/
    public static int getMinutes(Date startDate, Date endDate) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        int minute = (int) (end - start) / (1000 * 60);
        return minute;
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/21 11:23
     * @Description 获取时间
     **/
    public static Date getDate(int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
