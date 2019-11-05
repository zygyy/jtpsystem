package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



/**
 * create by: MmmLll_Shen
 * description:新建文件夹或者文件的vo请求类
 * create time: 10:56 2019/9/23
 */
@Getter
@Setter
public class AddFileReq {


    /**
     * 文件ID
     */
    private int fileId;
    /**
     * 文件名称
     */
    private String label;
    /**
     * 文件类型
     */
    private int fileType;
    /**
     * 文件备注
     */
    private String remark;
    /**
     * 文件所有者
     */
    private String fileOwner;
    /**
     * 文件创建日期
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;

    /**
     * 文件父节点ID
     */
    private int parentId;
    /**
     * 文件路径
     */
    private String filePath;





}
