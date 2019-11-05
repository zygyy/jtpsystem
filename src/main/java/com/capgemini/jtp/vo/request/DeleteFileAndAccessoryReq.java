package com.capgemini.jtp.vo.request;


import lombok.Getter;
import lombok.Setter;


/**
 * create by: MmmLll_Shen
 * description:删除文件的请求类
 * create time: 13:50 2019/9/22
 */
@Getter
@Setter
public class DeleteFileAndAccessoryReq {


    /**
     * 附件路径 + 名称
     */
    private String accessoryPathAndName;


    /**
     * create by: MmmLll_Shen
     * description:文件路径+名称
     * create time: 12:19 2019/10/10
     */
    private String filePathAndName;
}
