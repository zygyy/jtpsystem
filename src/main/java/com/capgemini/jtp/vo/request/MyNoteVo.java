package com.capgemini.jtp.vo.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MyNoteVo {
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
//    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT,timezone=DateUtils.DEFAULT_ZONE)
//    private Date createTime;
//    /**
//     * 创建者
//     */
//    private String createUser;
}
