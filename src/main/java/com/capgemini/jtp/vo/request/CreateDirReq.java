package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:新建文件夹请求类
 * create time: 13:52 2019/9/22
 */
@Getter
@Setter
public class CreateDirReq {
    /**
     *文件夹名称
     */
    private String fileName;

    /**
     * 文件夹路径
     */
    private String filePath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;
    /**
     * 文件夹所有者
     */
    private String fileOwner;

    /**
     * 文件夹的父节点id
     */
    private int parentId;


}
