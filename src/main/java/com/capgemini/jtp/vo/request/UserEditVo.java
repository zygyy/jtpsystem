package com.capgemini.jtp.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 10:07 2019/9/20
 */
@Data
public class UserEditVo {

    /**
     * 主键ID
     */
    private int userId;

    /**
     * 显示姓名
     */
    private String chineseName;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 登陆密码（加密）
     */
    private String password;

    //性别
    private String gender;

    //部门（从指定列表中选择）
    private int departId;

    //角色
    private int roleId;

    //用户状态
    private int userStateId;

    //头像上传
    private MultipartFile profile;
}
