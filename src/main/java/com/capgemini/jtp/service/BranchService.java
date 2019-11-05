package com.capgemini.jtp.service;

import com.capgemini.jtp.vo.request.BranchDeleteVo;
import com.capgemini.jtp.vo.request.BranchEditVo;
import com.capgemini.jtp.vo.response.BranchListVo;

import java.util.List;

public interface BranchService {

//    Map<Long, Branch> getAllBranchMap();

    List<BranchListVo> listBranchVo();

    Integer updateBranch(BranchEditVo branchEditVo);

    Integer addBranch(BranchEditVo branchEditVo);

    Integer deleteBranchByBranchId(BranchDeleteVo branchDeleteVo);
}
