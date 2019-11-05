package com.capgemini.jtp.vo.request;

//import com.capgemini.cn.deemo.vo.base.BaseSearchVo;
import com.capgemini.jtp.utils.DateUtils;
import com.capgemini.jtp.vo.base.BaseSearchVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hasaker
 * @since 2019/9/5 10:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MessageSearchVo extends BaseSearchVo {

    private String query;

    private int messageTypeId;

    /**
     * 搜索的开始时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 搜索结束时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 搜索的类型：
     *  0 表示没有特殊限定
     *  1 搜索本日
     *  2 搜索本周
     *  3 搜索本月
     */
    private int limit;
}
