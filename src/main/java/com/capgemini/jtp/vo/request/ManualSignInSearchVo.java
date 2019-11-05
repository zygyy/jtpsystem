package com.capgemini.jtp.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ManualSignInSearchVo {
    private String departName;
    private String branchName;
    private String username;
    private int  userId;

}
