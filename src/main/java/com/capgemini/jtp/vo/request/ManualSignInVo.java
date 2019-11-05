package com.capgemini.jtp.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString

public class ManualSignInVo {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;
    private int userId;
    private String signDesc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;
}
