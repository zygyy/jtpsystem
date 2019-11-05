package com.capgemini.jtp.vo.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:09
 */
@Data
@ApiModel
public class MessageEditVo {

    private int messageId;

    private int messageTypeId;

    private String messageTitle;

    private String messageContent;

    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private int createUserId;

    private List<Integer> recipientIds;
}
