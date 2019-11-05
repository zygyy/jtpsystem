package com.capgemini.jtp.vo.response;

import com.capgemini.jtp.entity.Schedule;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Data
@Component
public class DepartGetRespVo {
    private String createUser;
    private String chineseName;
    private Object  scheduleId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private List<String > dateList;
    private List<Integer > scheduleIdList;
    private List<ScheduleResVo> ScheduleList;
}
