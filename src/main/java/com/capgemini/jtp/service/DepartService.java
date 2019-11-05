package com.capgemini.jtp.service;

import com.capgemini.jtp.vo.request.DepartDeleteVo;
import com.capgemini.jtp.vo.request.DepartEditVo;
import com.capgemini.jtp.vo.response.DepartListVo;

import java.util.List;

public interface DepartService {

    List<DepartListVo> listDepartVo();

    Integer updateDepart(DepartEditVo departEditVo);

    Integer addDepart(DepartEditVo departEditVo);

    Integer deleteDepartByDepartId(DepartDeleteVo departDeleteVo);
}
