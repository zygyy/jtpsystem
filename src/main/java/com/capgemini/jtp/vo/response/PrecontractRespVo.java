package com.capgemini.jtp.vo.response;

import lombok.Data;

@Data
public class PrecontractRespVo {
    /**
     * 用户Id
     */
    private String  userId;
    /**
     * 用户姓名
     */
    private String  userName;
    /**
     * 部门id
     */
    private int departId;
    /**
     * 部门名称
     */
    private String departName;
}
