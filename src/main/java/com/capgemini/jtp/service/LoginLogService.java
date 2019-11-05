package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.LoginLog;
import com.capgemini.jtp.vo.request.DeleteBatchVo;
import com.capgemini.jtp.vo.request.LoadLoginLogReq;
import com.capgemini.jtp.vo.response.LoginLogResp;

import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 14:50 2019/9/20
 */
public interface LoginLogService {

    public int insertSelective(LoginLog record);

    /**
     * @param loadLoginLog
     * @return
     */
    public List<LoginLogResp> selectLoginLog(LoadLoginLogReq loadLoginLog);


    /**
     * create by: MmmLll_Shen
     * description:根据登录日志的 id 删除日志
     * create time: 14:50 2019/9/20
     */
    public int deleteByPrimaryKey(int loginId);


    /**
     * create by: MmmLll_Shen
     * description:批量删除
     * create time: 16:41 2019/9/24
     */
    public Integer deleteLoginLofBatch(DeleteBatchVo deleteBatchVo);
}
