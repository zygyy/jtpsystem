package com.capgemini.jtp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: chunle.wang
 * @Date: 2019/09/02
 */

@Getter
@Setter
@ToString
public class OperateLog {
    /**
     *  操作日志的唯一标识
     */
    private Integer operateId;

    /**
     * 操作者，存储用户的 id
     */
    private Integer userId;

    /**
     * 操作名称
     */
    private String operateName;

    /**
     * 操作对象 ID
     */
    private String objectId;

    /**
     * 操作描述信息
     */
    private String operateDesc;

    /**
     * 操作时间
     */
    private Date operateTime;
}
