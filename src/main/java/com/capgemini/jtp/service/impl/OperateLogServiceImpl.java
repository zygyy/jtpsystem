package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.OperateLog;
import com.capgemini.jtp.mapper.OperatelogMapper;
import com.capgemini.jtp.service.OperateLogService;
import com.capgemini.jtp.utils.ConverLog;
import com.capgemini.jtp.utils.TimeFrame;
import com.capgemini.jtp.vo.request.DeleteBatchVo;
import com.capgemini.jtp.vo.request.OperateLogReq;
import com.capgemini.jtp.vo.response.OperateLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    OperatelogMapper operatelogMapper;

    /**
     * 添加操作日志
     * @param record
     * @return
     */
    @Override
    public int insert(OperateLog record) {
        return  operatelogMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer operateid) {
        return operatelogMapper.deleteByPrimaryKey(operateid);
    }

    @Override
    public List<OperateLogResp> selectAllOperateLog(OperateLogReq operateLogReq) {
        List<OperateLogResp> operateLogResps = new ArrayList<>();
        Integer choose = operateLogReq.getLimit();
        if (choose != null) {
            Date now = new Date();
            if (choose == 1) {
                operateLogReq.setStart(TimeFrame.startOfDay(now));
                operateLogReq.setEnd(TimeFrame.endOfDay(now));
            } else if (choose == 2) {
                operateLogReq.setStart(TimeFrame.firstDateOfWeek(now));
                operateLogReq.setEnd(TimeFrame.lastDateOfWeek(now));
            } else if (choose == 3) {
                operateLogReq.setStart(TimeFrame.firstDateOfWonth(now));
                operateLogReq.setEnd(TimeFrame.lastDateOfMonth(now));
            }
        }
        List<OperateLog> operateLogs = operatelogMapper.selectAllOperateLog(operateLogReq);
        for (OperateLog operateLog : operateLogs) {
            operateLogResps.add(ConverLog.operateLogToOperateLogResp(operateLog));
        }

        return operateLogResps;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteOperateLogBatch(DeleteBatchVo deleteBatchVo) {
        String[] list = deleteBatchVo.getDeleteList().split(",");
        Integer affectedRows = 0;
        for(String id : list){
            int operateId = Integer.valueOf(id);
            //删除该登录日志信息
            operatelogMapper.deleteByPrimaryKey(operateId);
            affectedRows++;
        }
        return affectedRows;
    }


}
