package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.OperateLog;
import com.capgemini.jtp.vo.request.OperateLogReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OperatelogMapper {

    int deleteByPrimaryKey(Integer operateId);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    OperateLog selectByPrimaryKey(Integer operateId);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKey(OperateLog record);

    List<OperateLog> selectAllOperateLog(@Param(value = "operateLogReq") OperateLogReq operateLogReq);
}