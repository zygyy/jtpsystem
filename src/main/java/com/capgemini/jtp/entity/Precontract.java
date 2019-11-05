package com.capgemini.jtp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Precontract {
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
