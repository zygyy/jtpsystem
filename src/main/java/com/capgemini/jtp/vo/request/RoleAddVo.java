package com.capgemini.jtp.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleAddVo {
    Integer roleId;
    private  String roleName;
    private   String roleDesc;
}
