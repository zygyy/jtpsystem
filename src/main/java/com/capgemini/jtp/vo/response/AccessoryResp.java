package com.capgemini.jtp.vo.response;

//import com.capgemini.cn.myoffice.utils.DateUtils;
import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *@Author: XuechengDi
 *@Description:
 *@Date:2019-08-30
 */


@Getter
@Setter
public class AccessoryResp {
    /**
     * 附件ID
     */
    private int accessoryId;

    /**
     *文件ID
     */
    private int fileId;

    /**
     * 附件名称
     */
    private String accessoryName;

    /**
     *附件大小
     */
    private  int accessorySize;

    /**
     *附件类型
     */
    private int accessoryType;

    /**
     *创建日期
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;

    /**
     * 附件路径
     */
    private String accessoryPath;

    //附件类型名
    private String accessoryTypeName;


}
