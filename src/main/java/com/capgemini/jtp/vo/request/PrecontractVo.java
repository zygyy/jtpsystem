package com.capgemini.jtp.vo.request;

import lombok.Data;

@Data
public class PrecontractVo {
    /**
     * 预约序号Id
     */
    private int preContractId;
    /**
     * 日程Id
     */
    private int scheduleId;
    /**
     * 预约人
     */
    private String userId;
}
