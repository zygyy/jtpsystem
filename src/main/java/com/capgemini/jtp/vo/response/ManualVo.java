package com.capgemini.jtp.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ManualVo {
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date signTime;
    private String signTag;
    private String signDesc;
    private String departName;
    private String branchName;
}
