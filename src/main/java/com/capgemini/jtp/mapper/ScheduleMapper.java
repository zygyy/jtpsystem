package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Schedule;
import com.capgemini.jtp.vo.request.DepartScheduleVo;
import com.capgemini.jtp.vo.response.DepartGetRespVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleMapper {
    List<Schedule> listSchedule();//返回所有内容
    Schedule listByScheduleId(@Param("scheduleId") int scheduleId);//返回所有内容
    List<Schedule> listScheduleByName(@Param("userName") String userName);//根据姓名返回所以内容

    Integer insertSchedule(@Param("Schedule") Schedule schedule);//新增 0失败 1成功
    Integer deleteScheduleById(@Param("scheduleId") int scheduleId);//0失败 1成功
    Integer updateScheduleById(@Param("Schedule") Schedule schedule);//更新 0失败 1成功 不更新作者
    List<DepartGetRespVo> getScheduleByName(@Param("departSchedule") DepartScheduleVo departScheduleVo);//模糊查询部门日程
    Integer getLastSchedule();//查询最后一条记录 scheduleId
    Integer getScheduleIdByCreateTime(@Param("createTimes") Date createTimes);//查询id根据创建时间
    String getChineseNameByUserName(@Param("userName")String userName);

}
