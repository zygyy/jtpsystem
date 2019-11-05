package com.capgemini.jtp.vo.response;

import com.capgemini.jtp.vo.base.BaseVo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserVo{
    //用户名（用户唯一标识）
    private int userId;
    //用户名
    private String username;
    //密码
    private String password;
    //中文名
    private String chineseName;
    //所在部门
    private int departId;
    //性别
    private String gender;
    //角色
    private int roleId;
    //用户状态
    private int userStateId;


}
