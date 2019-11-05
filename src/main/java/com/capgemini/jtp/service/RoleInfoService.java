package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.vo.request.AddPowerVo;
import com.capgemini.jtp.vo.request.MyNoteDeleteVo;
import com.capgemini.jtp.vo.request.RoleAddVo;
import com.capgemini.jtp.vo.request.RoleDeleteVo;
import com.capgemini.jtp.vo.response.RoleListVo;


import java.util.List;

public interface RoleInfoService {
    /**
     * 角色管理
     * @return
     */
    public   List<RoleListVo> roleInfolist();
    public Integer updateRole(RoleAddVo roleAddVo);
    public Integer deleteRole(int roleId);
    public Integer addRole(RoleAddVo roleAddVo);
    public Integer addPowerRole(AddPowerVo addPowerVo);

}
