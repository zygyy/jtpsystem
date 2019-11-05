package com.capgemini.jtp.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ManualSearchVo {
    private int userId;
    private String username;
    private String departName;
    private String branchName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date stopTime;
}
