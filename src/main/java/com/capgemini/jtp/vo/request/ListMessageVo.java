package com.capgemini.jtp.vo.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ListMessageVo {
    /**
     * 当前用户
     */
    private String userId;

    /**
     * 搜索开始时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD,timezone=DateUtils.DEFAULT_ZONE)
    private Date selectBeginTime;
    /**
     * 搜索截止时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD,timezone=DateUtils.DEFAULT_ZONE)
    private Date selectEndTime;

}
