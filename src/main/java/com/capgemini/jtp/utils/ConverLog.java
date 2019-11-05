package com.capgemini.jtp.utils;

import com.capgemini.jtp.entity.FileInfo;
import com.capgemini.jtp.entity.LoginLog;
//import com.capgemini.cn.myoffice.data.domain.OperateLog;
import com.capgemini.jtp.entity.OperateLog;
import com.capgemini.jtp.vo.response.FileSearchResp;
import com.capgemini.jtp.vo.response.LoginLogResp;
import com.capgemini.jtp.vo.response.OperateLogResp;
//import com.capgemini.jtp.vo.response.OperateLogResp;

import java.text.SimpleDateFormat;


/**
 * create by: MmmLll_Shen
 * description:将 vo 类中的信息转成实体类的信息
 * create time: 15:05 2019/9/20
 */
public class ConverLog {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将登录日志的实体类和登录日志的 vo 类转变
     * @param loginlog
     * @return
     */
    public static LoginLogResp LoginLogToLoginLogResp(LoginLog loginlog) {
        LoginLogResp loginLogResp = new LoginLogResp();

        loginLogResp.setLoginId(loginlog.getLoginId());
        loginLogResp.setUserId(loginlog.getUserId());
        loginLogResp.setChineseName(loginlog.getChineseName());
        loginlog.setUserId(loginlog.getUserId());
        // 设置日期格式
        loginLogResp.setLoginTime(simpleDateFormat.format(loginlog.getLoginTime()));

        loginLogResp.setIfSuccess(loginlog.getIfSuccess().equals("1") ? "是" : "否");
        loginLogResp.setLoginUserIp(loginlog.getLoginUserIp());
        loginLogResp.setLoginDesc(loginlog.getLoginDesc());

        return loginLogResp;
    }

    public static OperateLogResp operateLogToOperateLogResp(OperateLog operateLog) {
       OperateLogResp operateLogResp = new OperateLogResp();
       operateLogResp.setOperateDesc(operateLog.getOperateDesc());
       operateLogResp.setOperateId(operateLog.getOperateId());
       operateLogResp.setUserId(operateLog.getUserId());
       operateLogResp.setOperateName(operateLog.getOperateName());
       operateLogResp.setObjectId(operateLog.getObjectId());
       operateLogResp.setOperateTime(simpleDateFormat.format(operateLog.getOperateTime()));

        return  operateLogResp;
    }


    /**
     * 将文件基本信息类转为 FileSearchResp 类型
     * @param fileInfo
     * @return
     */
    public static FileSearchResp convertFileInfoToFileSearchResp(FileInfo fileInfo) {
        FileSearchResp fileSearchResp = new FileSearchResp();
        fileSearchResp.setFileId(fileInfo.getFileId());
        fileSearchResp.setLabel(fileInfo.getLabel());
        fileSearchResp.setFileOwner(fileInfo.getFileOwner());
        fileSearchResp.setCreateDate(fileInfo.getCreateDate());
        fileSearchResp.setFilePath(fileInfo.getFilePath());
        fileSearchResp.setFileType(fileInfo.getFileType());

        return fileSearchResp;
    }

}
