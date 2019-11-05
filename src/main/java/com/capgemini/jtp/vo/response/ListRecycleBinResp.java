package com.capgemini.jtp.vo.response;



import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:查询回收站文件的响应类
 * create time: 13:54 2019/9/22
 */
@Getter
@Setter
public class ListRecycleBinResp {

    /**
     * 文件Id
     */
    private int fileId;
    /**
     *文件名称
     */
    private String label;

    /**
     *文件路径
     */
    private String filePath;

    /**
     *文件类型
     */
    private int fileType;


    //文件所有者
    private String fileOwner;

    /**
     * 删除时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date deleteDate;

    private int lastParentId;

    private String fileTypeName;
}
