package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Depart;
import com.capgemini.jtp.vo.request.MessageUserSearchVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartMapper {

    List<Depart> listDepartVo();

//    List<Depart> getAllDeparts();

    Integer addDepart(@Param("depart") Depart depart);

    Integer updateDepart(@Param("depart") Depart depart);

    Integer deleteDepartByDepartId(@Param("depart") Depart depart);


    Depart getDepartment(@Param("departmentId") int departmentId);

    Integer selectDepartIdByDepartNameAndBranchId(@Param("messageUserSearchVo") MessageUserSearchVo messageUserSearchVo);


}
