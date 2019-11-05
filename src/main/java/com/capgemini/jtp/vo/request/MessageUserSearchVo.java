package com.capgemini.jtp.vo.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 10:07 2019/9/20
 */
@Data
public class MessageUserSearchVo {

    /**
     * 显示姓名
     */
    private String chineseName;

    //工号
    private int userId;

    //部门名
    private String departName;

    //机构名
    private String branchName;


}
