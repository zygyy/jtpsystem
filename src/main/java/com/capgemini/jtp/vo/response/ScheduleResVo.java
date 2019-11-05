package com.capgemini.jtp.vo.response;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ScheduleResVo {

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
    private String meetingName;
    /**
     * 日程开始时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 日程结束时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 日程内容
     */
    private String schContent;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 是否私有（0:否；1：私有）
     */
    private String ifPrivate;
}
