package com.capgemini.jtp.entity;


import lombok.Getter;
import lombok.Setter;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 13:34 2019/9/22
 */
@Getter
@Setter
public class FileTypeInfo {
    /**
     * 文件类型ID
     */
    private int fileTypeId;

    /**
     *文件类型名称
     */
    private String fileTypeName;

    /**
     *文件类型图标
     */
    private String fileTypeImage;

    /**
     *文件类型后缀
     */
    private String fileTypeSuffix;
}
