package com.capgemini.jtp.entity;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:文件管理的实体类
 * create time: 13:31 2019/9/22
 */
@Data
public class FileInfo {
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
     * 文件删除日期
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date deleteDate;
    /**
     * 文件父节点ID
     */
    private int parentId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件是否被删除标识  0 表示没有被删除 1表示为被删除
     */
    private String ifDelete;

    private int lastParentId;//??????????????????????????????

    private List<FileInfo> children =new ArrayList<>();







}
