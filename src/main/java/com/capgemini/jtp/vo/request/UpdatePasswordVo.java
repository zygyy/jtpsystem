package com.capgemini.jtp.vo.request;

import lombok.Data;

/**
 * 修改密码vo
 */
@Data
public class UpdatePasswordVo {

    private int userId;
    private String password;
}
