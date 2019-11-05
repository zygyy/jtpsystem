package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:新建附件的vo请求类
 * create time: 10:56 2019/9/23
 */
@Getter
@Setter
public class AddAccessoryReq {


    /**
     * 文件ID
     */
    private int fileId;
    /**
     * 文件名称
     */
    private String accessoryName;

    //附件大小
    private int accessorySize;

    //附件类型
    private int accessoryType;


    //创建时间
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;

    /**
     * 文件路径
     */
    private String accessoryPath;





}
