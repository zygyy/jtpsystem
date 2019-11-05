package com.capgemini.jtp.vo.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDeleteVo {

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
