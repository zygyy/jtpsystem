package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.Branch;
import com.capgemini.jtp.mapper.BranchMapper;
import com.capgemini.jtp.service.BranchService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.request.BranchDeleteVo;
import com.capgemini.jtp.vo.request.BranchEditVo;
import com.capgemini.jtp.vo.response.BranchListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {


    @Autowired
    BranchMapper branchMapper;

    @Override
    public List<BranchListVo> listBranchVo() {
        List<Branch> branchList = branchMapper.listBranchVo();
        List<BranchListVo> branchListVos = new ArrayList<BranchListVo>();
        for (Branch branch : branchList) {
            branchListVos.add(ConvertUtils.convertBranchEntityToListVo(branch));
        }
        return branchListVos;
    }

    @Override
    public Integer updateBranch(BranchEditVo branchEditVo){
        Branch branch = new Branch();
        branch.setBranchId(branchEditVo.getBranchId());
        branch.setBranchName(branchEditVo.getBranchName());
        branch.setBranchShortName(branchEditVo.getBranchShortName());
        return branchMapper.updateBranch(branch);
    }
    @Override
    public Integer addBranch(BranchEditVo branchEditVo){

        Branch branch = ConvertUtils.convertBranchVoToEntity(branchEditVo);
        return branchMapper.addBranch(branch);
    }

    @Override
    public Integer deleteBranchByBranchId(BranchDeleteVo branchDeleteVo){
        Branch branch=new Branch();
        branch.setBranchId(branchDeleteVo.getBranchId());
        return branchMapper.deleteBranchByBranchId(branch);
    }

//    @Override
//    public Map<Long, Branch> getAllBranchMap() {
//        Map<Long, Branch> BranchMap = new HashMap<Long, Branch>();
//        List<Branch> branchList = getAllBranches();
//        for (Branch branch : branchList) {
//            branchMap.put(branch.getBranchId(), branch);
//        }
//        return branchMap;
//    }
}
