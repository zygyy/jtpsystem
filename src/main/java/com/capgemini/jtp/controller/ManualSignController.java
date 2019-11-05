package com.capgemini.jtp.controller;

import com.capgemini.jtp.mapper.ManualMapper;
import com.capgemini.jtp.service.ManualSignService;

import com.capgemini.jtp.utils.TimeFrame;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.BranchVo;
import com.capgemini.jtp.vo.response.CountSignVo;
import com.capgemini.jtp.vo.response.DepartVo;
import com.capgemini.jtp.vo.response.ManualVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Api("考勤信息")
@RestController
@RequestMapping("/admin")
public class ManualSignController {
    @Autowired
    ManualSignService manualSignService;

    @Autowired
    ManualMapper manualMapper;

    @RequestMapping(value = "/sign/in", method = RequestMethod.POST)
    @ApiOperation(value = "签到")
    public RespBean insertSignIn(@RequestBody ManualSignInVo manualSignInVo, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        int userId = 0;
        if (object != null) {

            userId = Integer.valueOf(String.valueOf(object));
        }
        manualSignInVo.setUserId(userId);
        Date now = new Date();
        manualSignInVo.setStart(TimeFrame.startOfDay(now));
        manualSignInVo.setEnd(TimeFrame.endOfDay(now));
        manualSignInVo.setSignTime(new Date());
        if (manualMapper.isSign(manualSignInVo) >= 2) {
            return RespBean.error("您今日已签到成功，无法二次签到");
        } else {
            ManualSignInSearchVo manualSignInSearchVo = manualSignService.insertManualSign(manualSignInVo);
            if (manualSignInSearchVo != null) {
                return RespBean.ok(manualSignInSearchVo);

            } else
                return RespBean.error("签到失败");
        }

    }

    @RequestMapping(value = "/sign/off", method = RequestMethod.POST)
    @ApiOperation(value = "签退")
    public ManualSignInSearchVo insertSignOff(@RequestBody ManualSignOffVo manualSignOffVo, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("operationUserId");
        int userId = 0;

        if (object != null) {
            userId = Integer.valueOf(String.valueOf(object));
        }
        manualSignOffVo.setUserId(userId);
        ManualSignInSearchVo manualSignInSearchVo = manualSignService.insertManualSignOff(manualSignOffVo);
            return manualSignInSearchVo;


    }



   @RequestMapping("/ManualSearch")
    @ApiOperation("历史签到记录")
    public RespBean listManualSearch(@RequestBody ManualSearchVo manualSearchVo)
   {
       if(manualSearchVo.getStartTime()==null || manualSearchVo.getStopTime()==null){
           return RespBean.error("请输入具体查询时间");

       }else {
           List<ManualVo> viewSignList = manualSignService.listManualSearch(manualSearchVo);
           if (viewSignList != null) {
               return RespBean.ok(viewSignList);
           }
           return RespBean.error("查询失败！");
       }

   }
    @ApiOperation(value = "列出所有机构名称")
    @RequestMapping("/attendsearch/branch")
    public List<BranchVo> loadListBranch(){
        List<BranchVo> branchVoList =manualSignService.listBranch();
        if(branchVoList ==null){
            return null;
        }
        return branchVoList;
    }
    @ApiOperation(value = "根据机构列出部门名称")
    @GetMapping("/attendsearch/{branchName}")
    public List<DepartVo> loadListDepart(@PathVariable String branchName){
        List<DepartVo> departVoList = manualSignService.listDepart(branchName);
        if (departVoList == null){
            return null;
        }
        return  departVoList;
    }


    @ApiOperation(value = "查询考勤统计")
    @RequestMapping(value = "/attends")
    public RespBean countSign(@RequestBody CountSignSearchVo countSignSearchVo){
        List<CountSignVo> countSignRespList= manualSignService.listCountSign(countSignSearchVo);
        if (countSignRespList != null){
            return RespBean.ok(countSignRespList);

        }
        return RespBean.error("查询失败！");
    }


}

