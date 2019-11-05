package com.capgemini.jtp.service;


import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.BranchVo;
import com.capgemini.jtp.vo.response.CountSignVo;
import com.capgemini.jtp.vo.response.DepartVo;
import com.capgemini.jtp.vo.response.ManualVo;

import java.util.List;


public interface ManualSignService {
    public ManualSignInSearchVo insertManualSign(ManualSignInVo manualSignInVo);
    public ManualSignInSearchVo insertManualSignOff(ManualSignOffVo manualSignOffVo);
    public List<ManualVo> listManualSearch(ManualSearchVo manualSearchVo);
    public List<DepartVo> listDepart(String branchName);
    public List<BranchVo> listBranch();
    public List<CountSignVo> listCountSign(CountSignSearchVo countSignSearchVo);

}
