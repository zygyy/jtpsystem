package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.Precontract;
import com.capgemini.jtp.entity.Schedule;
import com.capgemini.jtp.mapper.MeetingInfoMapper;
import com.capgemini.jtp.mapper.PrecontractMapper;
import com.capgemini.jtp.mapper.ScheduleMapper;
import com.capgemini.jtp.mapper.UserMapper;
import com.capgemini.jtp.service.MyScheduleService;
import com.capgemini.jtp.vo.request.DepartScheduleVo;
import com.capgemini.jtp.vo.request.ScheduleAddVo;
import com.capgemini.jtp.vo.request.ScheduleVo;
import com.capgemini.jtp.vo.response.DepartGetRespVo;
import com.capgemini.jtp.vo.response.ScheduleResVo;
import com.capgemini.jtp.vo.response.ScheduleRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MyScheduleServiceImpl implements MyScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    PrecontractMapper precontractMapper;
    @Autowired
    Schedule schedule;
    @Autowired
    Precontract precontract;
    @Autowired
    ScheduleRespVo scheduleRespVo;
    @Autowired
    MeetingInfoMapper meetingInfoMapper;
    @Autowired
    DepartScheduleVo departScheduleVo;
    @Autowired
    DepartGetRespVo departGetRespVo;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    Date date;
    String dateString;
    /**
     * 返回所有的个人日程信息
     * @return
     */
    public List<Schedule> listAllSchedule(String  userName){
        return scheduleMapper.listScheduleByName( userName);
    };

    /**
     * 返回某个人的日程信息
     * @param scheduleId
     * @return
     */
    public ScheduleRespVo listMySchedule( int scheduleId){

        scheduleRespVo.setSchedule(scheduleMapper.listByScheduleId(scheduleId));
        int meetingId = scheduleRespVo.getSchedule().getMeetingId();
        scheduleRespVo.setMeetingInfo(meetingInfoMapper.selectMeetingById(meetingId));
        scheduleRespVo.setPrecontractUser(precontractMapper.precontractUserByScheduleId(scheduleId));
        return scheduleRespVo;

    };

    /**
     * 添加一个个人日程
     * @param
     * @return
     */
    public Integer addMySchedule(ScheduleAddVo scheduleAddVo){
        //把VO赋值到Schedule实体类
        schedule.setTitle(scheduleAddVo.getTitle());
        schedule.setAddress(scheduleAddVo.getAddress());
        schedule.setMeetingId(scheduleAddVo.getMeetingId());
        schedule.setBeginTime(scheduleAddVo.getBeginTime());
        schedule.setEndTime(scheduleAddVo.getEndTime());
        schedule.setSchContent(scheduleAddVo.getSchContent());
        schedule.setCreateUser(scheduleAddVo.getCreateUser());
        schedule.setCreateTime(scheduleAddVo.getCreateTime());
        schedule.setIfPrivate(scheduleAddVo.getIfPrivate());
        //把日程添加到数据库
        Integer integer = scheduleMapper.insertSchedule(schedule);
        //添加预约信息
//        if (scheduleAddVo.getUserIdList().size()>0)
////        {
////
////            precontract.setScheduleId(scheduleMapper.getScheduleIdByCreateTime(scheduleAddVo.getCreateTime()));//判断一下数据库是否创建时间唯一一条
////            //precontract.setUserId(scheduleAddVo.getUserId());
////            List<String> list= scheduleAddVo.getUserIdList();//所以预约的人
////            for (String s:list){
////                precontract.setUserId(s);
////                integer  &= precontractMapper.insertPrecontract(precontract);
////            }
////
////        }
        return integer;

    };

    /**
     * 修改一个个人日程
     * @param
     * @return
     */
    public Integer editMySchedule(ScheduleVo scheduleVo){
        //把VO赋值到Schedule实体类
        schedule.setScheduleId(scheduleVo.getScheduleId());
        schedule.setTitle(scheduleVo.getTitle());
        schedule.setAddress(scheduleVo.getAddress());
        schedule.setMeetingId(scheduleVo.getMeetingId());
        schedule.setBeginTime(scheduleVo.getBeginTime());
        schedule.setEndTime(scheduleVo.getEndTime());
        schedule.setSchContent(scheduleVo.getSchContent());
        //schedule.setCreateUser(scheduleVo.getCreateUser());
        //schedule.setCreateTime(scheduleVo.getCreateTime());
        schedule.setIfPrivate(scheduleVo.getIfPrivate());
        //修改日程数据库
        Integer integer =scheduleMapper.updateScheduleById(schedule);
        //
        //更新预约信息表
//        precontract.setScheduleId(scheduleVo.getScheduleId());
//        precontractMapper.deleteaPrecByScheduleId(precontract.getScheduleId());
//        List<String> list= scheduleVo.getUserIdList();
//        for (String s:list){
//            precontract.setUserId(s);
//             integer &= precontractMapper.insertPrecontract(precontract);
//        }


       // precontract.setUserId(scheduleVo.getUserId());
        //返回所有该日程id的所有预约id
//        List<Integer> list= precontractMapper.selectPrecByScheduleId(scheduleVo.getScheduleId());
//        for (Integer i:list) {
//            precontract.setPreContractId(i);
//            precontractMapper.updatePrecontractById(precontract);
//        }



        return integer;
    };

//    /**
//     * 日程预约他人
//     * @param
//     * @return
//     */
//    public Integer reservePerson(PrecontractVo precontractVo){
//        precontract.setScheduleId(precontractVo.getScheduleId());
//        precontract.setUserId(precontractVo.getUserId());
//        return precontractMapper.insertPrecontract(precontract);
//    };
//    public Integer reserveDelete(PrecontractVo precontractVo){
//
//        return precontractMapper.deletePrecontractById(precontractVo.getPreContractId());
//    };

    /**
     * 删除一个日程
     * @param
     * @return
     */
    public Integer deleteMySchedule(int scheduleId){
        //删除相关预约信息
        precontractMapper.deleteaPrecByScheduleId(scheduleId);
        return scheduleMapper.deleteScheduleById(scheduleId);
    };
    /**
     * 部门日程模糊搜索
     */
    public List<DepartGetRespVo> departSchedule(DepartScheduleVo departScheduleVo){

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(departScheduleVo.getSelectTime());//设置时间为查询时间
        int week0=calendar.get(Calendar.DAY_OF_WEEK);//判断是周几

        calendar.add(Calendar.DATE,8-week0);//本周最后第一天
        departScheduleVo.setTime2(calendar.getTime());


        calendar.add(Calendar.DATE,-7);//本周第一天
        departScheduleVo.setTime1(calendar.getTime());

        List<DepartGetRespVo> departGetRespVoList =scheduleMapper.getScheduleByName(departScheduleVo);//部门日程搜索结果
        Iterator<DepartGetRespVo> iterator = departGetRespVoList.iterator();//遍历

        while (iterator.hasNext()){
            DepartGetRespVo d=iterator.next();
            List<String > dateList=new ArrayList<>();
            int m=0;
            calendar.setTime(departScheduleVo.getTime1());//设置时间为第一天
            calendar.add(Calendar.DATE,-1);
            while (m<7){//本周每天的日期
                SimpleDateFormat simp02=new SimpleDateFormat("dd");

                calendar.add(Calendar.DATE,1);

                date=calendar.getTime();
                dateString=simp02.format(date);
                dateList.add(dateString);

                m++;
            }
            d.setDateList(dateList);
            d.setChineseName(scheduleMapper.getChineseNameByUserName(d.getCreateUser()));//获取中文名字
            String s=(String)d.getScheduleId();//获得的每个创建者符合条件的日程Id字符串
            List<Integer> list=new ArrayList<>();
            List<ScheduleResVo> list1=new ArrayList<>();
            for (int i=0;i<7;i++){
                list.add(null);
                list1.add(null);
            }
            for(String retval:s.split(",")){//将字符串分开
                Schedule schedule= scheduleMapper.listByScheduleId(Integer.valueOf(retval));
                ScheduleResVo scheduleResVo = new ScheduleResVo();
                scheduleResVo.setScheduleId(schedule.getScheduleId());
                scheduleResVo.setTitle(schedule.getTitle());
                scheduleResVo.setAddress(schedule.getAddress());
                scheduleResVo.setMeetingId(schedule.getMeetingId());
                scheduleResVo.setMeetingName(meetingInfoMapper.selectMeetingById(schedule.getMeetingId()).getMeetingName());
                scheduleResVo.setBeginTime(schedule.getBeginTime());
                scheduleResVo.setEndTime(schedule.getEndTime());
                scheduleResVo.setSchContent(schedule.getSchContent());
                scheduleResVo.setCreateUser(schedule.getCreateUser());
                scheduleResVo.setCreateTime(schedule.getCreateTime());
                scheduleResVo.setIfPrivate(schedule.getIfPrivate());
                calendar.setTime(schedule.getBeginTime());//将查询到的日程放到一周中对应的位置
                int week=calendar.get(Calendar.DAY_OF_WEEK);
                list.set(week-1,Integer.valueOf(retval));
                list1.set(week-1,scheduleResVo);
            }
            d.setScheduleIdList(list);
            d.setScheduleList(list1);
        }
        return departGetRespVoList;


    }
}
