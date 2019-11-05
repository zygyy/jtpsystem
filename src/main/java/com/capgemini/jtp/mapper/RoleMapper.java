package com.capgemini.jtp.mapper;



import com.capgemini.jtp.entity.Role;

import com.capgemini.jtp.vo.request.AddVo;
import com.capgemini.jtp.vo.request.RoleAddVo;
import com.capgemini.jtp.vo.response.RoleListVo;
import com.capgemini.jtp.vo.response.UserStateRespVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<RoleListVo> listRole();
    Integer updateRoleById(@Param("roleAdd") RoleAddVo roleAdd);
    Integer deleteRoleById(int roleId);
    Integer addRoleById(@Param("roleAddVo") RoleAddVo roleAddVo);
    List<UserStateRespVo> listUserState();
    List<Role> getListRole();
    Integer addPowerById(@Param("addVo") AddVo addVo);
    Integer isPowerById(int roleId);
    Integer deletePowerById(int roleId);
    Integer addPowerByIdOne(int roleId);
    Integer isPowerByNodeId(int nodeId);
}
