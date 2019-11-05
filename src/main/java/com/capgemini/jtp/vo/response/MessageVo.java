package com.capgemini.jtp.vo.response;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:14
 */
@Data
public class MessageVo {

    /**
     * ID
     */
    private int messageId;

    /**
     * 消息类型ID
     */
    private int messageTypeId;

    /**
     * 消息类型
     */
    private String messageTypeName;

    /**
     * 标题
     */
    private String messageTitle;

    /**
     * 内容
     */
    private String messageContent;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 发送对象集合
     */
    private List<UserVo> recipients;

    //创建时间
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 消息创建用户ID
     */
    private int createUserId;

    /**
     * 消息创建用户姓名
     */
    private String createUserName;

    /**
     * 消息是否已发布
     */
    private String isPublished;

    /**
     * 消息发布时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishTime;


    //消息是否被当前用户已读
    private String ifReadByUser;
}
