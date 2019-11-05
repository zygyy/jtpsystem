package com.capgemini.jtp.vo.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserMassageRespVo {
    private int userId;
    private String username;
    private String chineseName;
    private String password;

    private int departId;
    private String departName;//

    private String gender;

    private int roleId;
    private String roleName;//

    private int userStateId;//
    private String userStateName;


}
