package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.mapper.RoleMapper;
import com.capgemini.jtp.service.RoleInfoService;
import com.capgemini.jtp.vo.request.AddPowerVo;
import com.capgemini.jtp.vo.request.AddVo;
import com.capgemini.jtp.vo.request.RoleAddVo;
import com.capgemini.jtp.vo.request.RoleDeleteVo;
import com.capgemini.jtp.vo.response.RoleListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    RoleMapper roleMapper;


    @Override

/**
 * 角色管理
 */
    public List<RoleListVo> roleInfolist() {
        return roleMapper.listRole();
    }

    @Override
    public Integer updateRole(RoleAddVo roleAdd) {
        return roleMapper.updateRoleById(roleAdd);
    }

    @Override
    public Integer deleteRole(int roleId) {
        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public Integer addRole(RoleAddVo roleAddVo) {
        return roleMapper.addRoleById(roleAddVo) ;
    }

    @Override
    public Integer addPowerRole(AddPowerVo addPowerVo) {
        List<Integer> a=addPowerVo.getNodeIds();
        AddVo addVo =new AddVo();

        /**
         * 去重
         */
        List<Integer> list=new ArrayList();

            for(int i=0;i<a.size();i++){
                if(!list.contains(a.get(i))){
                    list.add(a.get(i));
                }
            }


        int sta=0;
        if(roleMapper.isPowerById(addPowerVo.getRoleId())!=0){
            roleMapper.deletePowerById(addPowerVo.getRoleId());
            roleMapper.addPowerByIdOne(addPowerVo.getRoleId());
        }
           for (int b : list){
               addVo.setRoleId(addPowerVo.getRoleId());
               addVo.setNodeId(b);
               sta=roleMapper.addPowerById(addVo);

        }
return sta;

}}
