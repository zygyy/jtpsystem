package com.capgemini.jtp.vo.response;



import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:文件搜索的响应类
 * create time: 13:56 2019/9/22
 */
@Getter
@Setter
public class FileSearchResp {
    //文件Id
    private int fileId;

    /**
     * 文件名称
     */
    private String label;

    /**
     * 所在文件夹
     */
    private String filePath;

    /**
     * 文件类型
     */
    private int fileType;

    /**
     * 文件所有者
     */
    private String fileOwner;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;
}
