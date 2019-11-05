package com.capgemini.jtp.vo.request;

import lombok.Data;

@Data
public class PrecontractAddVo {

    /**
     * 日程Id
     */
    private int scheduleId;
    /**
     * 预约人
     */
    private String userId;
}
