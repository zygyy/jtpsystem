package com.capgemini.jtp.vo.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScheduleVo {
    /**
     * 日程Id
     */
    private int scheduleId;
    /**
     * 日程标题
     */
    private String title;
    /**
     * 会议地址
     */
    private String address;
    /**
     * 会议类型
     */
    private int meetingId;
    /**
     * 日程开始时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    private Date beginTime;
    /**
     * 日程结束时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    private Date endTime;
    /**
     * 日程内容
     */
    private String schContent;
//    /**
//     * 创建者
//     */
//    private String createUser;
    /**
     * 创建时间
     */
//    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
//    private Date createTime;
    /**
     * 是否私有（0:否；1：私有）
     */
    private String ifPrivate;
    /**
     * 预约人
     */
    //private String userId;
    private List<String> userIdList;
}
