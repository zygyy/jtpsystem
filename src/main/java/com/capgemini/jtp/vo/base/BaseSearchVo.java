package com.capgemini.jtp.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BaseSearchVo
 * @Description: 搜索vo基础类
 * @Author: Brady
 * @Date: 5/30/2019 4:44 PM
 */
@Getter
@Setter
public class BaseSearchVo {

    @ApiModelProperty(value = "分页起始位置")
    private Integer start = 0;

    @ApiModelProperty(value = "分页大小")
    private Integer size = 15;

}
