package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.ManualSign;
import com.capgemini.jtp.mapper.ManualMapper;
import com.capgemini.jtp.service.ManualSignService;
import com.capgemini.jtp.utils.ConvertSignUtils;

import com.capgemini.jtp.utils.TimeFrame;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.BranchVo;
import com.capgemini.jtp.vo.response.CountSignVo;
import com.capgemini.jtp.vo.response.DepartVo;
import com.capgemini.jtp.vo.response.ManualVo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service

public class ManualSignServiceImpl implements ManualSignService {

    @Autowired
    ManualMapper manualMapper;

    @Override
    public ManualSignInSearchVo insertManualSign(ManualSignInVo manualSignInVo) {
        ManualSign manualSign = ConvertSignUtils.convertAddManualSignToManualSign(manualSignInVo);
        manualSign.setUserId(manualSignInVo.getUserId());
        manualMapper.insertManualSign(manualSign);
        ManualSignInSearchVo manualSignInSearchVo= manualMapper.searchManual(manualSignInVo.getUserId());
        return manualSignInSearchVo;

    }

    @Override
    public ManualSignInSearchVo insertManualSignOff(ManualSignOffVo manualSignOffVo) {
        ManualSign manualSign= ConvertSignUtils.convertAddManualSignToManualSignOff(manualSignOffVo);
        manualSign.setSignTime(new Date());
        manualSign.setUserId(manualSignOffVo.getUserId());
        manualMapper.insertManualSignOff(manualSign);
        ManualSignInSearchVo manualSignInSearchVo= manualMapper.searchManual(manualSignOffVo.getUserId());
        return manualSignInSearchVo;
    }

//    @Override
//    public ManualSignInSearchVo listSignInVo(int userId) {
//
//        ManualSignInSearchVo manualSignInSearchVo= manualMapper.searchManual(userId);
//        return manualSignInSearchVo;
//    }

    @Override
    public List<ManualVo> listManualSearch(ManualSearchVo manualSearchVo) {

        List<ManualVo> viewSignList = manualMapper.SearchManualHistory(manualSearchVo);
        for(ManualVo manualVo:viewSignList){
            if(manualVo.getSignTag().equals("1")){
                manualVo.setSignTag("签到");

            }else
                manualVo.setSignTag("签退");

        }
         return viewSignList;

    }

    @Override
    public List<BranchVo> listBranch() {
        return manualMapper.listBranch();
    }

    @Override
    public List<DepartVo> listDepart(String branchName) {
        return manualMapper.listDepart(branchName);
    }

    @Override
    public List<CountSignVo> listCountSign(CountSignSearchVo countSignSearchVo) {

//        统计时间段内有多少个周六周日,与其他天数
        Date start=countSignSearchVo.getStartTime();
        Calendar sta = Calendar.getInstance();// 从一个 Calendar 对象中获取 Date 对象
        sta.setTime(start);// 所以我们必需先获得一个实例，然后设置 Date 对象
        Date stop=countSignSearchVo.getStopTime();
        int countTime=0;
        while(start.getTime()<stop.getTime()){
         //计算周
            if (sta.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    sta.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {//      weekend+=1;
            }
            else {
                countTime += 1;
            }
            sta.add(Calendar.DAY_OF_YEAR, 1);//一年当中的第几天
            start= sta.getTime();//为实现下一层循环，进行天数递增
        }

        List<CountSignVo> countSignList = manualMapper.listCountSign(countSignSearchVo);
        List<CountSignVo> countSignRespList =new ArrayList<>();
        String name =" ";
        for (CountSignVo countSignVo : countSignList){

            //获取名字并传入Mapper
            if(name.equals(countSignVo.getUserName())){continue;}
            name=countSignVo.getUserName();
            countSignSearchVo.setUserName(name);
            countSignVo.setLate(manualMapper.countLate(countSignSearchVo));
            countSignVo.setLeaveEarly(manualMapper.countLeaveEarly(countSignSearchVo));
            int contyear=manualMapper.listYearTime(countSignSearchVo);
            countSignVo.setAbsenteeism(countTime-contyear);
            DecimalFormat df=new DecimalFormat("0.00");//定义结果格式
            String percentage = df.format((float)((float)contyear/(float)countTime)*(float)100);
            countSignVo.setAttendance(percentage+"%");
            countSignRespList.add(countSignVo);
        }

        return countSignRespList;
    }
    }

