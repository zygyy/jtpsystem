package com.capgemini.jtp.vo.request;


import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


/**
 * create by: MmmLll_Shen
 * description:文件及相应附件请求类
 * create time: 14:07 2019/9/22
 */
@Getter
@Setter
public class FileAndAccessoryReq {
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

    //附件
    private MultipartFile multipartFile;

    /**
     * 文件路径
     */
    private String accessoryPath;
}
