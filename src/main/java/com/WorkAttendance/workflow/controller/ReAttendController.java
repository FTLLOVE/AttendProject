package com.WorkAttendance.workflow.controller;


import com.WorkAttendance.user.entity.User;
import com.WorkAttendance.workflow.entity.ReAttend;
import com.WorkAttendance.workflow.service.ReAttendService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("reAttend")
public class ReAttendController {

    @Resource
    private ReAttendService reAttendService;

    @RequestMapping()
    public String toReAttend(Model model, HttpSession session) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        List<ReAttend> reAttendList = reAttendService.listReAttend(user.getUsername());
        model.addAttribute("reAttendList", reAttendList);
        return "reAttend";
    }


    @RequestMapping("/start")
    public void startReAttendFlow(@RequestBody ReAttend reAttend) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        reAttend.setReAttendStarter(user.getRealName());
        reAttendService.startReAttendFlow(reAttend);
    }

    @RequiresPermissions("reAttend:list")
    @RequestMapping("/list")
    public String listReAttendFlow(Model model, HttpSession session) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        String userName = user.getUsername();
        List<ReAttend> tasks = reAttendService.listTasks(userName);
        model.addAttribute("tasks", tasks);
        return "reAttendApprove";
    }


    @RequestMapping("/approve")
    public void approveReAttendFlow(@RequestBody ReAttend reAttend) {
        reAttendService.approve(reAttend);
    }

}
