package com.capgemini.jtp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@ApiModel(value = "考勤信息的实体类")
public class ManualSign {
    /**
     * 签卡Id
     */
    private Integer signId;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 签卡时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date signTime;

    /**
     * 签卡备注
     */
    private String signDesc;

    /**
     * 签卡标记
     */
    private Integer signTag;
}
