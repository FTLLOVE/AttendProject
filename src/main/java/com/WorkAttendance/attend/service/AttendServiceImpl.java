package com.WorkAttendance.attend.service;

import com.WorkAttendance.attend.dao.AttendMapper;
import com.WorkAttendance.attend.entity.Attend;
import com.WorkAttendance.attend.vo.QueryCondition;
import com.WorkAttendance.common.page.PageQueryBean;
import com.WorkAttendance.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 打卡记录逻辑:
 * 1. 首先是判断当前打卡时间是早上还是下午
 */
@Service(value = "attendServiceImpl")
public class AttendServiceImpl implements AttendService {

    // 记录中午的时间间隔,判断上下午
    private static final int NOON_HOUR = 12;
    private static final int NOON_MINUTE = 00;

    // 早晚上班时间
    private static final int MORNING_HOUR = 9;
    private static final int MORNING_MINUTE = 30;
    private static final int EVENING_HOUR = 18;
    private static final int EVENING_MINUTE = 30;

    // 缺勤一天(按照8小时的工作时间来算)
    private static final Integer ABSENCE_DAY = 480;

    // 考勤异常的状态
    private static final Byte ATTEND_STATUS_NORMAL = 1;
    private static final byte ATTEND_STATUS_ABNORMAL = 2;

    private Log log = LogFactory.getLog(AttendServiceImpl.class);

    @Resource
    private AttendMapper attendMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");

    /**
     * @Author fangtongle
     * @Date 2017/8/21 23:05
     * @Description 打卡的简单逻辑
     **/
    @Transactional
    @Override
    public void signAttend(Attend attend) throws Exception {
        try {
            Date noon = DateUtils.getDate(NOON_HOUR, NOON_MINUTE);
            Date today = new Date();
            attend.setAttendDate(today);
            attend.setAttendWeek((byte) DateUtils.getWeek()); // 获取当前是周几
            // 根据用户的userID 和当天的时间 来查询用户用户
            Attend todayRecord = attendMapper.selectTodaySignRecord(attend.getUserId());
            Date morningAttend = DateUtils.getDate(MORNING_HOUR, MORNING_MINUTE);
            // 要是用户今天没有打卡情况的话那么就让用户打卡,再判断用户是早上还是中午打卡
            if (todayRecord == null) {
                // 这边首先判断是否早上打卡，然后判断是否是九点后打的卡
                if (today.compareTo(noon) <= 0) {
                    attend.setAttendMorning(today);
                    if (today.compareTo(morningAttend) > 0) {
                        attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        attend.setAbsence(DateUtils.getMinutes(morningAttend, today));
                    }
                } else {
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            } else {
                // 如果用户存在并且是上午打卡的话就算正常打卡，因为打过卡的话那肯定是记录最早的打卡记录，不能重新覆盖，直接结束
                if (today.compareTo(noon) <= 0) {
                    return;
                } else {
                    todayRecord.setAttendEvening(today);
                    Date eveningAttend = DateUtils.getDate(EVENING_HOUR, EVENING_MINUTE);
                    if (today.compareTo(eveningAttend) < 0) {
                        todayRecord.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        todayRecord.setAbsence(DateUtils.getMinutes(today, eveningAttend));
                    } else {
                        todayRecord.setAttendStatus(ATTEND_STATUS_NORMAL);
                        todayRecord.setAbsence(0);
                    }
                }
                attendMapper.updateByPrimaryKeySelective(todayRecord);
            }
        } catch (Exception e) {
            log.error("用户打卡异常");
            throw e;
        }
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/21 23:10
     * @Description 1. 首先是根据condition查询是否存在数据 2. 要是查询到才进行下面的SQL分页查询
     **/
    @Override
    @Transactional
    public PageQueryBean listAttend(QueryCondition condition) {
        int count = attendMapper.countByCondition(condition);
        PageQueryBean pageQueryBean = new PageQueryBean();
        if (count > 0) {
            pageQueryBean.setTotalRows(count);
            pageQueryBean.setCurrentPage(condition.getCurrentPage());
            pageQueryBean.setPageSize(condition.getPageSize());
            List<Attend> list = attendMapper.selectAttendPage(condition);
            pageQueryBean.setItems(list);
        }
        return pageQueryBean;
    }

    /**
     * @Author fangtongle
     * @Date 2017/8/24 17:12
     * @Description 这边是检验员工是否打卡，也就是检查异常的处理
     **/
    @Override
    @Transactional
    public void checkAttend() {
        // 查询没有打卡的员工的ID
        List<Long> userIdList = attendMapper.selectTodayAbsence();
        if (CollectionUtils.isNotEmpty(userIdList)) {
            List<Attend> attendList = new ArrayList<>();
            for (Long userId : userIdList) {
                Attend attend = new Attend();
                attend.setUserId(userId);
                attend.setAttendDate(new Date());
                attend.setAttendWeek((byte) DateUtils.getWeek());
                attend.setAbsence(ABSENCE_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendList.add(attend);
            }
            // 批量插入
            attendMapper.batchInsert(attendList);
        }

        // 检查晚打卡的记录
        List<Attend> absenceList = attendMapper.selectTodayEveningAbsence();
        if (CollectionUtils.isNotEmpty(absenceList)) {
            for (Attend attend : absenceList) {
                attend.setAbsence(ABSENCE_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendMapper.updateByPrimaryKeySelective(attend);
            }
        }

    }

}
