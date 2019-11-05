package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.Depart;
import com.capgemini.jtp.mapper.BranchMapper;
import com.capgemini.jtp.mapper.DepartMapper;
import com.capgemini.jtp.service.DepartService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.request.DepartDeleteVo;
import com.capgemini.jtp.vo.request.DepartEditVo;
import com.capgemini.jtp.vo.response.DepartListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DepartServiceImpl implements DepartService {

    @Autowired
    DepartMapper departMapper;
    @Autowired
    BranchMapper branchMapper;


    @Override
    public List<DepartListVo> listDepartVo() {
        List<Depart> departList = departMapper.listDepartVo();
        List<DepartListVo> departListVos = new ArrayList<DepartListVo>();
        for (Depart depart : departList) {
            DepartListVo departListVo= ConvertUtils.convertDepartEntityToListVo(depart);
            departListVo.setBranchName(branchMapper.getBranchNameById(depart.getBranchId()));

            departListVos.add(departListVo);
        }
        return departListVos;
    }



    @Override
    public Integer updateDepart(DepartEditVo departEditVo){
        Depart depart = new Depart();
        depart.setDepartId(departEditVo.getDepartId());
        depart.setDepartName(departEditVo.getDepartName());
        depart.setPrincipalUser(departEditVo.getPrincipalUser());
        depart.setConnectTelNo(departEditVo.getConnectTelNo());
        depart.setConnectMobileTelNo(departEditVo.getConnectMobileTelNo());
        depart.setFaxes(departEditVo.getFaxes());
        depart.setBranchId(departEditVo.getBranchId());
        return departMapper.updateDepart(depart);
    }
    @Override
    public Integer addDepart(DepartEditVo departEditVo){
        Depart depart = ConvertUtils.convertDepartVoToEntity(departEditVo);
        return departMapper.addDepart(depart);
    }

    @Override
    public Integer deleteDepartByDepartId(DepartDeleteVo departDeleteVo){
        Depart depart=new Depart();
        depart.setDepartId(departDeleteVo.getDepartId());
        return departMapper.deleteDepartByDepartId(depart);
    }
}
