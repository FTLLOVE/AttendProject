package com.WorkAttendance.attend.controller;


import com.WorkAttendance.attend.entity.Attend;
import com.WorkAttendance.attend.service.AttendService;
import com.WorkAttendance.attend.vo.QueryCondition;
import com.WorkAttendance.common.page.PageQueryBean;
import com.WorkAttendance.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "attend")
public class AttendController {

    @Resource
    private AttendService attendService;

    /**
     * @Author fangtongle
     * @Date 2017/8/21 22:05
     * @Description 打卡记录
     **/
    @RequestMapping()
    public String attend() {
        return "attend";
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/21 22:06
     * @Description 模拟虚拟的打卡记录
     **/
    @RequestMapping(value = "/sign")
    @ResponseBody
    public String toAttend(@RequestBody Attend attend,HttpSession session) throws Exception {
        attendService.signAttend(attend);
        return "success";
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/21 23:07
     * @Description 考勤数据分页查询
     **/
    @RequiresPermissions("attend:attendList")
    @RequestMapping(value = "/attendList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition condition, HttpSession session) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        String [] rangeDate = condition.getRangeDate().split("/");
        condition.setStartDate(rangeDate[0]);
        condition.setEndDate(rangeDate[1]);
        condition.setUserId(user.getId());
        PageQueryBean result = attendService.listAttend(condition);
        return result;
//        User user = (User) session.getAttribute("userInfo");
//        String[] rangeDate = condition.getRangeDate().split("/");
//        condition.setStartDate(rangeDate[0]);
//        condition.setEndDate(rangeDate[1]);
//        condition.setUserId(user.getId());
//        PageQueryBean result = attendService.listAttend(condition);
//        return result;
    }


}
