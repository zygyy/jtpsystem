package com.capgemini.jtp.vo.request;

import lombok.Data;

@Data
public class MeetingInfoVo {
    /**
     * 会议类型Id
     */
    private int meetingId;
    /**
     * 会议类型名称
     */
    private String meetingName;
}
