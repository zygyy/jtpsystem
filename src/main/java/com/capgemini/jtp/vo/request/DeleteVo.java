package com.capgemini.jtp.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 10:32 2019/9/25
 */
@Data
@ApiModel
public class DeleteVo {

    @ApiModelProperty("所要删除的Id集合")
    List<Integer> ids;
}
