package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Branch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BranchMapper {

    List<Branch> listBranchVo();

    Integer addBranch(@Param("branch") Branch branch);

    Integer updateBranch(@Param("branch") Branch branch);

    Integer deleteBranchByBranchId(@Param("branch") Branch branch);
    String getBranchNameById(@Param("branchId" )int branchId);
}
