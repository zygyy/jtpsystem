package com.capgemini.jtp.vo.response;



import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:文件详细信息的响应类
 * create time: 13:55 2019/9/22
 */
@Getter
@Setter
public class FileDetailResp {
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
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private String accessorySize;
}
