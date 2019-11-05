package com.capgemini.jtp.mapper;


import com.capgemini.jtp.entity.LoginLog;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface LoginLogMapper {

    int deleteByPrimaryKey(int loginId);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(int loginId);

    /**
     * 使用 java.util.Date 时间格式是 年月日
     * @return
     */
    List<LoginLog> selectLoginLog(Date start, Date end);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}