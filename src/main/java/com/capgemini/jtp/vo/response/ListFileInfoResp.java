package com.capgemini.jtp.vo.response;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ListFileInfoResp {
    /**
     * 文件名称
     */
    private String label;

    /**
     * 文件类型名称
     */
    private String fileTypeName;
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

    private int parentId;
}
