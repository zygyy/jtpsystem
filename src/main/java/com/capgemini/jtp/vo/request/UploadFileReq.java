package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UploadFileReq {

    /**
     *附件
     */
   // private MultipartFile file;

    /**
     * 附件Id
     */
    private int fileId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件名称
     */
    private String accessoryName;

    /**
     *附件类型
     */
    private int accessoryType;

    /**
     * 附件大小
     */
    private int accessorySize;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS, timezone=DateUtils.DEFAULT_ZONE)
    private Date createDate;

    /**
     * 附件所有者
     */
    private String fileOwner;

    /**
     * 父节点Id
     */
    private int parentId;

    /**
     * 父文件夹路径
     */
    private String parentPath;

    /**
     * 附件路径
     */
    private String accessoryPath;




}
