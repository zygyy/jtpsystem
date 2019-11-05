package com.capgemini.jtp.vo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountSignVo {

    /**
     *  签卡员工
     */

    private String userName;

    /**
     *  出勤率
     */

    private String attendance;
    /**
     *  迟到次数
     */

    private Integer late;
    /**
     *  早退次数
     */

    private Integer leaveEarly;
    /**
     *  旷工次数
     */

    private Integer absenteeism;
    /**
     *  部门名称
     */

    private String departName;
    /**
     *  机构名称
     */

    private String branchName;
}
