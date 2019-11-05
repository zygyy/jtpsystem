package com.capgemini.jtp.vo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListVo {
    /**
     * 角色名称
     */
    private int roleId;
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
}
