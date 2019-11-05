package com.capgemini.jtp.vo.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDeleteVo {
    private int branchId;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
