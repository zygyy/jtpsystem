package com.capgemini.jtp.vo.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DepartListVo {
    private int departId;
    private String departName;
    private String principalUser;
    private long connectTelNo;
    private long connectMobileTelNo;
    private long faxes;
    private int branchId;
    private String branchName;

//    public String getBranchName() {
//        return branchName;
//    }
//
//    public void setBranchName(String branchName) {
//        this.branchName = branchName;
//    }
//
//    public String getDepartName() {
//        return departName;
//    }
//
//    public void setDepartName(String departName) {
//        this.departName = departName;
//    }
//
//    public String getPrincipalUser() {
//        return principalUser;
//    }
//
//    public void setPrincipalUser(String principalUser) {
//        this.principalUser = principalUser;
//    }
//
//    public long getConnectTelNo() {
//        return connectTelNo;
//    }
//
//    public void setConnectTelNo(int connectTelNo) {
//        this.connectTelNo = connectTelNo;
//    }
//
//    public long getConnectMobileTelNo() {
//        return connectMobileTelNo;
//    }
//
//    public void setConnectMobileTelNo(int connectMobileTelNo) {
//        this.connectMobileTelNo = connectMobileTelNo;
//    }
//
//    public long getFaxes() {
//        return faxes;
//    }
//
//    public void setFaxes(int faxes) {
//        this.faxes = faxes;
//    }
//
//    public int getBranchId() {
//        return branchId;
//    }
//
//    public void setBranchId(int branchId) {
//        this.branchId = branchId;
//    }
}
