package com.capgemini.jtp.entity;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class MyNote {
    /**
     * 便签Id
     */
    private int noteId;
    /**
     * 便签标题
     */
    private String noteTitle;
    /**
     * 便签内容
     */
    private String noteContent;
    /**
     * 创建时间
     */

    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 创建者
     */
    private String createUser;
}
