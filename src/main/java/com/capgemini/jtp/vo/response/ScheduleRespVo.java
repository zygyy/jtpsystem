package com.capgemini.jtp.vo.response;

import com.capgemini.jtp.entity.MeetingInfo;
import com.capgemini.jtp.entity.Schedule;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ScheduleRespVo {

    /**
     * 会议类型信息
     */
    private MeetingInfo meetingInfo;
    /**
     * 日程信息
     */
    private Schedule schedule;
    /**
     * 预约人信息
     */
    List<PrecontractRespVo> precontractUser;

}
