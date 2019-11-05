package com.capgemini.jtp.vo.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 13:23 2019/9/22
 */
@Getter
@Setter
public class ListFileInfoReq {
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id", required = true)
    private int parentId;
}
