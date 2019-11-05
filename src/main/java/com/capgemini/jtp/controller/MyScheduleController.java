package com.capgemini.jtp.controller;


import com.capgemini.jtp.entity.Schedule;
import com.capgemini.jtp.service.MyScheduleService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.DepartScheduleVo;
import com.capgemini.jtp.vo.request.ScheduleAddVo;
import com.capgemini.jtp.vo.request.ScheduleDeleteVo;
import com.capgemini.jtp.vo.request.ScheduleVo;
import com.capgemini.jtp.vo.response.DepartGetRespVo;
import com.capgemini.jtp.vo.response.ScheduleRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api("日程管理")
@RequestMapping("/myschedule")
public class MyScheduleController {
    @Autowired
    MyScheduleService myScheduleService;
    //返回所有内容
    @ApiOperation(value = "我的日程列表")
    @ResponseBody
    @RequestMapping(value = "/listallschedule/{userName}", method = RequestMethod.GET)
    public RespBean listMySchedule(@PathVariable("userName")String  userName) {
        List<Schedule> list=myScheduleService.listAllSchedule(userName);
        if (list.size()>0)
            return RespBean.ok("便签查询成功",list);
        else
            return RespBean.error("便签查询失败");
    }
    @ApiOperation(value = "指定日程信息")
    @ResponseBody
    @GetMapping("/listmyschedule/{scheduleId}")
    public RespBean listMySchedule(@PathVariable("scheduleId")int scheduleId) {
        ScheduleRespVo scheduleRespVo=myScheduleService.listMySchedule(scheduleId);
        if(scheduleRespVo != null)
        return RespBean.ok("日程信息查询成功",scheduleRespVo) ;
        else
            return RespBean.error("日程信息查询失败");
    }
    //增加一个个人日程
    @ApiOperation(value = "新增我的日程")
    @ResponseBody
    @RequestMapping(value = "/addmyschedule", method = RequestMethod.POST)
    public RespBean addMySchedule(@Valid @RequestBody ScheduleAddVo scheduleAddVo) {
        if(myScheduleService.addMySchedule(scheduleAddVo)!=0)
            return RespBean.ok("新增日程成功");
        else
            return RespBean.error("新增日程失败");
    }

    //只需要接受scheduleId
    @ApiOperation(value = "删除我的日程")
    @ResponseBody
    @RequestMapping(value = "/deletemyschedule", method = RequestMethod.POST)
    public RespBean deleteMySchedule(@Valid @RequestBody ScheduleDeleteVo scheduleDeleteVo) {
        if( myScheduleService.deleteMySchedule(scheduleDeleteVo.getScheduleId())!=0)
            return RespBean.ok("删除日程成功");
        else
            return RespBean.error("删除日程失败");
    }


    @ApiOperation(value = "修改我的日程")
    @ResponseBody
    @RequestMapping(value = "/editmyschedule", method = RequestMethod.POST)
    public RespBean editMySchedule(@Valid @RequestBody ScheduleVo scheduleVo) {
        if( myScheduleService.editMySchedule(scheduleVo)!=0)
            return RespBean.ok("修改日程成功");
        else
            return RespBean.error("修改日程失败");
    }
    @ApiOperation(value = "部门日程搜索")
    @ResponseBody
    @RequestMapping(value = "/departschedule", method = RequestMethod.POST)
    public RespBean editMySchedule(@Valid @RequestBody DepartScheduleVo departScheduleVo) {
        List<DepartGetRespVo> list= myScheduleService.departSchedule(departScheduleVo);
        if (list.size()>0)
            return RespBean.ok("部门日程搜索成功",list);
        else
            return RespBean.error("部门日程搜索失败");

    }


//    @ApiOperation(value = "预约他人")
//    @ResponseBody
//    @RequestMapping(value = "/reserveperson", method = RequestMethod.POST)
//    public Integer reservePerson(@Valid @RequestBody PrecontractVo precontractVo) {
//        return myScheduleService.reservePerson(precontractVo);
//    }
//    @ApiOperation(value = "删除预约")
//    @ResponseBody
//    @RequestMapping(value = "/reservedelete", method = RequestMethod.POST)
//    public Integer reserveDelete(@Valid @RequestBody PrecontractVo precontractVo) {
//        return myScheduleService.reservePerson(precontractVo);
//    }
}

