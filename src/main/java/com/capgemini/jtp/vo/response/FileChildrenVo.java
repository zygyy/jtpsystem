package com.capgemini.jtp.vo.response;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * create by: MmmLll_Shen
 * description:
 * create time: 17:06 2019/9/27
 */
@Data
public class FileChildrenVo {

    /**
     * ID
     */
    private int fileId;

    //文件名称
    private String label;

    //文件类型
    private int fileType;

    //文件类型名称
    private String fileTypeName;

    //注解remark
    private String remark;

    //文件所有者
    private String fileOwner;

    //文件创造时间
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //文件父级文件
    private int parentId;

    //文件路径
    private String filePath;

    //文件子文件
    private List<FileChildrenVo> fileChildrenVoList;

}
