package com.capgemini.jtp.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * create by: MmmLll_Shen
 * description:查询附件的请求类
 * create time: 13:48 2019/9/22
 */
@Getter
@Setter
public class ListAccessoryReq {
    /**
     * 文件Id
     */
    @ApiModelProperty(value = "父文件id",required = true)
    private int fileId;
}
