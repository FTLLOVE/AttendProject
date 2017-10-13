package com.WorkAttendance.workflow.service;


import com.WorkAttendance.workflow.entity.ReAttend;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author fangtongle
 * @Date 2017/8/29 22:17
 * @Description
 **/
public interface ReAttendService {

    void startReAttendFlow(ReAttend reAttend);

    List<ReAttend> listTasks(String userName);

    void approve(ReAttend reAttend);

    List<ReAttend> listReAttend(String username);


}
