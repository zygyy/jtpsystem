package com.capgemini.jtp.vo.request;

import lombok.Data;

@Data
public class DepartEditVo {
    private int departId;
    private String departName;
    private String principalUser;
    private long connectTelNo;
    private long connectMobileTelNo;
    private long faxes;
    private int branchId;

//    public int getDepartId() {
//        return departId;
//    }
//
//    public void setDepartId(int departId) {
//        this.departId = departId;
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
//    public int getConnectTelNo() {
//        return connectTelNo;
//    }
//
//    public void setConnectTelNo(int connectTelNo) {
//        this.connectTelNo = connectTelNo;
//    }
//
//    public int getConnectMobileTelNo() {
//        return connectMobileTelNo;
//    }
//
//    public void setConnectMobileTelNo(int connectMobileTelNo) {
//        this.connectMobileTelNo = connectMobileTelNo;
//    }
//
//    public int getFaxes() {
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
