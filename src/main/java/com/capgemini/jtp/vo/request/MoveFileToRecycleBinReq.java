package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:将文件放入回收站的请求类
 * create time: 13:52 2019/9/22
 */
@Getter
@Setter
public class MoveFileToRecycleBinReq {
    /**
     * 文件Id
     */
    private int fileId;

    private int parentId;

//    /**
//     * 删除时间
//     */
//    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
//    private Date deleteDate;
}
