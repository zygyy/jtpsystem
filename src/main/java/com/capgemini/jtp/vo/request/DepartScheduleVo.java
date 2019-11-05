package com.capgemini.jtp.vo.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class DepartScheduleVo {
    /**
     * 机构Id
     */
   // private int branchId;
    /**
     * 部门Id
     */
    private  int departId;
    /**
     * 查找的姓名
     */
    private String Name;

    /**
     * 日期
     */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD,timezone=DateUtils.DEFAULT_ZONE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date selectTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time1;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date time2;


    /**
     * 登录人的用户名
     */
    private String userName;
}
