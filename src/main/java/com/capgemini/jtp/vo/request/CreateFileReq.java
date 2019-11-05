package com.capgemini.jtp.vo.request;



import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;



/**
 * create by: MmmLll_Shen
 * description:新建文件的请求类
 * create time: 13:50 2019/9/22
 */
@Getter
@Setter
public class CreateFileReq {
    /**
     *文件名称
     */
    private String fileName;

    /**
     *文件位置
     */
    private String filePath;

    /**
     *备注
     */
    private String remark;

    /**
     *创建时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;

    /**
     * 文件所有者
     */
    private String fileOwner;

    /**
     * 文件类型
     */
    private int fileType;

    /**
     * 父节点id
     */
    private int parentId;
}
