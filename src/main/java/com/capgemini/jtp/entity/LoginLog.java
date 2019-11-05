package com.capgemini.jtp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 14:33 2019/9/20
 */
@Getter
@Setter
@ToString
public class LoginLog {

    /**
     * 登录日志的 id
     */
    private int loginId;

    /**
     * 登录用户的 id
     */
    private int userId;

    /**
     * 登录的用户的名字
     */
    private String chineseName;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录结果
     */
    private String ifSuccess;

    /**
     * 登录用户的 Ip
     */
    private String loginUserIp;

    /**
     * 登录描述
     */
    private String loginDesc;
}