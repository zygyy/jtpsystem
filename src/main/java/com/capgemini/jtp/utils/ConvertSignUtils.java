package com.capgemini.jtp.utils;

import com.capgemini.jtp.entity.ManualSign;
import com.capgemini.jtp.vo.request.ManualSignInVo;
import com.capgemini.jtp.vo.request.ManualSignOffVo;


public class ConvertSignUtils {
    public static ManualSign convertAddManualSignToManualSign(ManualSignInVo manualSignInVo){
        ManualSign manualSign =new ManualSign();
        manualSign.setUserId(manualSignInVo.getUserId());
        manualSign.setSignDesc(manualSignInVo.getSignDesc());
        manualSign.setSignTime(manualSignInVo.getSignTime());
        return manualSign;
    }
    public static ManualSign convertAddManualSignToManualSignOff(ManualSignOffVo manualSignOffVo){
        ManualSign manualSign =new ManualSign();
        manualSign.setUserId(manualSignOffVo.getUserId());
        manualSign.setSignDesc(manualSignOffVo.getSignDesc());
        manualSign.setSignTime(manualSignOffVo.getSignTime());
        return manualSign;
    }
}
