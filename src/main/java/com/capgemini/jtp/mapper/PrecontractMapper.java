package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Precontract;
import com.capgemini.jtp.vo.response.PrecontractRespVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecontractMapper {
    List<Precontract> listPrecontract();//返回所有内容
    List<Precontract> listPrecByScheduleId(@Param("scheduleId") int scheduleId);//通过日程id返回预约信息；
    Integer insertPrecontract(@Param("Precontract") Precontract precontract);//新增 0失败 1成功
    Integer deletePrecontractById(@Param("preContractId") int preContractId);//0失败 1成功
    Integer updatePrecontractById(@Param("Precontract") Precontract precontract);//更新 0失败 1成功 不更新作者
    Integer deleteaPrecByScheduleId(@Param("scheduleId") int scheduleId);//删除预约信息根据日程Id
    List<Integer> selectPrecByScheduleId(@Param("scheduleId") int scheduleId);//通过日程查询预约Id
    List<PrecontractRespVo> precontractUserByScheduleId(@Param("scheduleId") int scheduleId);//返回该日程的预约人信息
}
