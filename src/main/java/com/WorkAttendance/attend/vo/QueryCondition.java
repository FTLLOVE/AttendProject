package com.WorkAttendance.attend.vo;

import com.WorkAttendance.common.page.PageQueryBean;

/**
 * @Author fangtongle
 * @Date 2017/8/21 23:04
 * @Description 带有时间间隔的分页查询
 **/
public class QueryCondition extends PageQueryBean {

    private long userId;

    private String startDate;

    private String endDate;

    private String rangeDate;

    private Byte attendStatus;

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    public Byte getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(Byte attendStatus) {
        this.attendStatus = attendStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
