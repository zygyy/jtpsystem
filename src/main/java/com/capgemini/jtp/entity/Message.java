package com.capgemini.jtp.entity;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class Message {
    /**
     * 消息Id
     */

    private int messageId;
    /**
     * 消息类型
     */
    private int type;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;

    /**
     * 开始有效时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 有效结束时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 发送者
     */
    private int fromUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 是否已发布（0未发布，1已发布）
     */
    private String ifPublish;
    /**
     * 发送时间
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date recordTime;


    /**
     * 接收用户ID
     */
    private List<Integer> recipientIds;
}
