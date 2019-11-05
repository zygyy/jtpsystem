package com.capgemini.jtp.vo.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 9:51 2019/9/21
 */
@Getter
@Setter
@ToString
public class OperateLogResp {
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
    private String operateTime;
}
