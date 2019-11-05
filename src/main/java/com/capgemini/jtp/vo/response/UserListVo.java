package com.capgemini.jtp.vo.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserListVo {
    private int userId;
    //用户名
    private String username;
    //密码
    private String password;
    //中文名
    private String chineseName;
    //角色
    private int roleId;


}
