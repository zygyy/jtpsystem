package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.ManualSign;
import com.capgemini.jtp.vo.request.CountSignSearchVo;
import com.capgemini.jtp.vo.request.ManualSignInVo;
import com.capgemini.jtp.vo.response.BranchVo;
import com.capgemini.jtp.vo.response.CountSignVo;
import com.capgemini.jtp.vo.response.DepartVo;
import com.capgemini.jtp.vo.request.ManualSearchVo;
import com.capgemini.jtp.vo.request.ManualSignInSearchVo;
import com.capgemini.jtp.vo.response.ManualVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ManualMapper {
    Integer insertManualSign(@Param("manualSign") ManualSign manualSign);
    Integer insertManualSignOff(@Param("manualSign") ManualSign manualSign);
    ManualSignInSearchVo searchManual(int userId);
    List<ManualVo>  SearchManualHistory(@Param("manualSearchVo") ManualSearchVo manualSearchVo);
    List<BranchVo> listBranch();
    List<DepartVo> listDepart(String branchName);
    List<CountSignVo> listCountSign(@Param("CountSignSearchVo") CountSignSearchVo countSignSearchVo);
    Integer countLate(@Param("CountSignSearchVo")CountSignSearchVo countSignSearchVo);
    Integer countLeaveEarly(@Param("CountSignSearchVo")CountSignSearchVo countSignSearchVo);
    Integer listYearTime(@Param("CountSignSearchVo")CountSignSearchVo countSignSearchVo);
    Integer isSign(@Param("ManualSignInVo") ManualSignInVo manualSignInVo);
}
