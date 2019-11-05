package com.capgemini.jtp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MeetingInfo {
    /**
     * 会议类型Id
     */
    private int meetingId;
    /**
     * 会议类型名称
     */
    private String meetingName;
}
