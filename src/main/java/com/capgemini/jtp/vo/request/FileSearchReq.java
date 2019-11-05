package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



/**
 * create by: MmmLll_Shen
 * description:文件搜索的请求类
 * create time: 13:36 2019/9/22
 */
@Getter
@Setter
public class FileSearchReq {
    /**
     * 文件名称
     */
    private String label;

    /**
     * 文件所有者
     */
    private String fileOwner;

    /**
     *起始时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date endDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;



    /**
     * 搜索的类型：
     *  0 表示没有特殊限定
     *  1 搜索本日
     *  2 搜索本周
     *  3 搜索本月
     */
    private int limit;

}
