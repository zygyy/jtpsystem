package com.capgemini.jtp.entity;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class MessageTrans {
    /**
     * 序号Id
     */
    private int id;
    /**
     * 消息Id
     */
    private int messageId;
    /**
     * 发送给对象Id
     */
    private int toUserId;
    /**
     * 是否已读，已读是1 未读是0；
     */
    private String ifRead;

}
