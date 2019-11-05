package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.LoginLog;
import com.capgemini.jtp.mapper.LoginLogMapper;
import com.capgemini.jtp.service.LoginLogService;
import com.capgemini.jtp.utils.ConverLog;
import com.capgemini.jtp.utils.TimeFrame;
import com.capgemini.jtp.vo.request.DeleteBatchVo;
import com.capgemini.jtp.vo.request.LoadLoginLogReq;
import com.capgemini.jtp.vo.response.LoginLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 15:32 2019/9/20
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

//    @Autowired
//    UserInfoServiceImpl userInfoService;

    @Override
    public int insertSelective(LoginLog record) {
        return loginLogMapper.insertSelective(record);
    }

    /**
     * 根据前台传递的 LoadLoginLog 对象中对于选择时间的限定
     * （0 按照选中的时间；1 选中当天；2 选中本周；3 选中本月；）
     * @param loadLoginLog
     * @return
     */
    @Override
    public List<LoginLogResp> selectLoginLog(LoadLoginLogReq loadLoginLog) {
        Integer choose = loadLoginLog.getLimit();
        if (choose != null) {
            Date now = new Date();
            if (choose == 1) {
                loadLoginLog.setStart(TimeFrame.startOfDay(now));
                loadLoginLog.setEnd(TimeFrame.endOfDay(now));
            } else if (choose == 2) {
                loadLoginLog.setStart(TimeFrame.firstDateOfWeek(now));
                loadLoginLog.setEnd(TimeFrame.lastDateOfWeek(now));
            } else if (choose == 3) {
                loadLoginLog.setStart(TimeFrame.firstDateOfWonth(now));
                loadLoginLog.setEnd(TimeFrame.lastDateOfMonth(now));
            }
        }
        List<LoginLog> loginLogs = loginLogMapper.selectLoginLog(loadLoginLog.getStart(), loadLoginLog.getEnd());
        List<LoginLogResp> loginLogResps = new ArrayList<>();
        for (LoginLog loginLog : loginLogs) {
            loginLogResps.add(ConverLog.LoginLogToLoginLogResp(loginLog));
        }
        return loginLogResps;
    }

    @Override
    public int deleteByPrimaryKey(int loginId) {
        return  loginLogMapper.deleteByPrimaryKey(loginId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteLoginLofBatch(DeleteBatchVo deleteBatchVo) {
        String[] list = deleteBatchVo.getDeleteList().split(",");
        Integer affectedRows = 0;
        for(String id : list){
            int loginId = Integer.valueOf(id);
            //删除该登录日志信息
            loginLogMapper.deleteByPrimaryKey(loginId);
            affectedRows++;
        }
        return affectedRows;
    }
}
