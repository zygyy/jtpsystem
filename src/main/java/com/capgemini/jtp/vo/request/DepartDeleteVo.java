package com.capgemini.jtp.vo.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartDeleteVo {
    private int departId;

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }
}
