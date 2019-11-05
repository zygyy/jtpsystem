package com.capgemini.jtp.entity;

import java.io.Serializable;


/**
 * create by: MmmLll_Shen
 * description:权限表
 * create time: 11:14 2019/9/18
 */
public class Role implements Serializable {

    private int roleId;
    private String roleName;
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
