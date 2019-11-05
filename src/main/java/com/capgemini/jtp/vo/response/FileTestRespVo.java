package com.capgemini.jtp.vo.response;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:文件管理的实体类
 * create time: 13:31 2019/9/22
 */
@Getter
@Setter
public class FileTestRespVo {
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
  

}
