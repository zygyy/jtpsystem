package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Schedule;
import com.capgemini.jtp.vo.request.DepartScheduleVo;
import com.capgemini.jtp.vo.request.ScheduleAddVo;
import com.capgemini.jtp.vo.request.ScheduleVo;
import com.capgemini.jtp.vo.response.DepartGetRespVo;
import com.capgemini.jtp.vo.response.ScheduleRespVo;

import java.util.List;

public interface MyScheduleService {
    public List<Schedule> listAllSchedule(String userName);
    public ScheduleRespVo listMySchedule(int scheduleId);
    public Integer addMySchedule(ScheduleAddVo scheduleAddVo);
    public Integer editMySchedule(ScheduleVo scheduleVo);
//    public Integer reservePerson(PrecontractVo precontractVo);
//    public Integer reserveDelete(PrecontractVo precontractVo);
    public Integer deleteMySchedule(int scheduleId);
    public List<DepartGetRespVo> departSchedule(DepartScheduleVo departScheduleVo);
}
