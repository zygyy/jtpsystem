package com.capgemini.jtp.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 14:58 2019/9/20
 */
@Getter
@Setter
@ToString
public class LoadLoginLogReq {

    /**
     * 搜索的开始时间
     */
    private Date start;

    /**
     * 搜索结束时间
     */
    private Date end;

    /**
     * 搜索的类型：
     *  0 表示没有特殊限定
     *  1 搜索本日
     *  2 搜索本周
     *  3 搜索本月
     */
    private int limit;
}
