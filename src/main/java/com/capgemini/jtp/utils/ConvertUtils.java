package com.capgemini.jtp.utils;


import com.capgemini.jtp.entity.*;
import com.capgemini.jtp.mapper.BranchMapper;
import com.capgemini.jtp.mapper.DepartMapper;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * create by: MmmLll_Shen
 * description:
 * create time: 16:11 2019/9/18
 */
public class ConvertUtils {

    /**
     * 将FileInfo 类型 转为 FileInfoResp类型
     * @param fileInfo
     * @return
     */
    public static ListFileInfoResp convertFileInfoToListFileInfoResp(FileInfo fileInfo) {
        ListFileInfoResp listFileInfoResp = new ListFileInfoResp();
        listFileInfoResp.setLabel(fileInfo.getLabel());
        listFileInfoResp.setRemark(fileInfo.getRemark());
        listFileInfoResp.setFileOwner(fileInfo.getFileOwner());
        listFileInfoResp.setCreateDate(fileInfo.getCreateDate());
        listFileInfoResp.setParentId(fileInfo.getParentId());
        return listFileInfoResp;

    }
    /**
     *@Author: XuechengDi
     *@Description:编辑文件请求转换为文件基本信息
     *@Date:2019-08-30
     */
    public static FileInfo convertEditFileInfoReqToFileInfo(EditFileInfoReq editFileInfoReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(editFileInfoReq.getFileId());
        fileInfo.setLabel(editFileInfoReq.getLabel());
        fileInfo.setRemark(editFileInfoReq.getRemark());
        return fileInfo;
    }
    /**
     *@Author: XuechengDi
     *@Description:编辑文件请求转换为文件类型信息
     *@Date:2019-08-30
     */
//    public static FileTypeInfo convertEditFileInfoReqToFileTypeInfo(EditFileInfoReq editFileInfoReq) {
//        FileTypeInfo fileTypeInfo = new FileTypeInfo();
//
//        fileTypeInfo.setFileTypeImage(editFileInfoReq.getFileTypeImage());
//        return fileTypeInfo;
//    }


    /**
     * 将新建文件的信息转为FileInfo类型
     * @return
     */
    public static FileInfo convertAddFileInfoToFileInfo(AddFileReq addFileReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setLabel(addFileReq.getLabel());
        fileInfo.setFileType(addFileReq.getFileType());
        fileInfo.setFileOwner(addFileReq.getFileOwner());
        fileInfo.setCreateDate(new Date());
        fileInfo.setParentId(addFileReq.getParentId());
        if(addFileReq.getRemark() != null && addFileReq.getRemark() != ""){
            fileInfo.setRemark(addFileReq.getRemark());
        }

//        fileInfo.setFilePath(addFileReq.getFilePath());
        return fileInfo;
    }


    /**
     * 将新建文件的信息转为FileInfo类型
     * @return
     */
    public static AccessoryInfo convertAddAccessoryInfoToAccessoryInfo(AddAccessoryReq addAccessoryReq) {
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        accessoryInfo.setFileId(addAccessoryReq.getFileId());
        accessoryInfo.setAccessoryName(addAccessoryReq.getAccessoryName());
        accessoryInfo.setAccessorySize(addAccessoryReq.getAccessorySize());
        accessoryInfo.setCreateDate(new Date());
        accessoryInfo.setAccessoryType(addAccessoryReq.getAccessoryType());

        return accessoryInfo;
    }

    /**
     * 将上传文件的信息转为FileInfo类型
     * @param uploadFileReq
     * @return
     */
    public static FileInfo convertUploadFileInfoToFileInfo(UploadFileReq uploadFileReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setLabel(uploadFileReq.getAccessoryName());
        fileInfo.setFileType(uploadFileReq.getAccessoryType());
        fileInfo.setFileOwner(uploadFileReq.getFileOwner());
        fileInfo.setCreateDate(uploadFileReq.getCreateDate());
        fileInfo.setParentId(uploadFileReq.getParentId());
        fileInfo.setRemark(uploadFileReq.getRemark());
        return fileInfo;
    }

    /**
     * 将上传文件的信息转为 AccessoryInfo 类型
     * @param uploadFileReq
     * @return
     */
    public static AccessoryInfo convertUploadFileInfoToAccessoryInfo(UploadFileReq uploadFileReq) {
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        accessoryInfo.setFileId(uploadFileReq.getFileId());
        accessoryInfo.setAccessoryName(uploadFileReq.getAccessoryName());
        accessoryInfo.setAccessorySize(uploadFileReq.getAccessorySize());
        accessoryInfo.setAccessoryType(uploadFileReq.getAccessoryType());
        accessoryInfo.setCreateDate(uploadFileReq.getCreateDate());
        return accessoryInfo;
    }



    /**
     *
     * 将新建文件请求类 转为 FileInfo类型
     * @param createFileReq
     * @return
     */

    public static FileInfo convertCreateFileInfoToFileInfo(CreateFileReq createFileReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setLabel(createFileReq.getFileName());
        fileInfo.setRemark(createFileReq.getRemark());
        fileInfo.setParentId(createFileReq.getParentId());
        fileInfo.setFileOwner(createFileReq.getFileOwner());
        fileInfo.setCreateDate(createFileReq.getCreateDate());
        fileInfo.setFileType(createFileReq.getFileType());
        fileInfo.setFilePath(createFileReq.getFilePath());
        return fileInfo;
    }

    /**
     * 将新建目录的请求类转为 FileInfo 类型
     * @param createDirReq
     * @return
     */

    public static FileInfo convertCreateDirInfoToFileInfo(CreateDirReq createDirReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setLabel(createDirReq.getFileName());
        fileInfo.setFilePath(createDirReq.getFilePath());
        fileInfo.setRemark(createDirReq.getRemark());
        fileInfo.setCreateDate(createDirReq.getCreateDate());
        fileInfo.setFileOwner(createDirReq.getFileOwner());
        fileInfo.setParentId(createDirReq.getParentId());
        fileInfo.setFileType(1);
        return fileInfo;
    }

    /**
     * 将把文件移动到回收站的请求类 转为 FileInfo 类型
     * @param moveFileToRecycleBinReq
     * @return
     */

    public static FileInfo convertMoveFileToRecycleBinReqToFileInfo(MoveFileToRecycleBinReq moveFileToRecycleBinReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(moveFileToRecycleBinReq.getFileId());
        fileInfo.setDeleteDate(new Date());
        fileInfo.setParentId(moveFileToRecycleBinReq.getParentId());
        return fileInfo;
    }


    /**
     * 将文件基本信息类转为 ListRecycleBinResp 类型
     * @param fileInfo
     * @return
     */
    public static ListRecycleBinResp convertFileInfoToListRecycleBinResp(FileInfo fileInfo) {
        ListRecycleBinResp listRecycleBinResp = new ListRecycleBinResp();
        listRecycleBinResp.setFileId(fileInfo.getFileId());
        listRecycleBinResp.setLabel(fileInfo.getLabel());
        listRecycleBinResp.setFilePath(fileInfo.getFilePath());
        listRecycleBinResp.setFileOwner(fileInfo.getFileOwner());
        listRecycleBinResp.setDeleteDate(fileInfo.getDeleteDate());
        listRecycleBinResp.setLastParentId(fileInfo.getLastParentId());
        return listRecycleBinResp;
    }

    /**
     * 将还原文件的请求类 转为 FileInfo 类型
     * @param fileReductionReq
     * @return
     */

    public static FileInfo convertFileReductionReqToFileInfo(FileReductionReq fileReductionReq) {

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(fileReductionReq.getFileId());
//        fileInfo.setLastParentId(fileReductionReq.getLastParentId());
        return fileInfo;

    }


    /**
     * 将文件基本信息类转为 FileSearchResp 类型
     * @param fileInfo
     * @return
     */
    public static FileSearchResp convertFileInfoToFileSearchResp(FileInfo fileInfo) {
        FileSearchResp fileSearchResp = new FileSearchResp();
        fileSearchResp.setLabel(fileInfo.getLabel());
        fileSearchResp.setFileOwner(fileInfo.getFileOwner());
        fileSearchResp.setCreateDate(fileInfo.getCreateDate());
        fileSearchResp.setFilePath(fileInfo.getFilePath());
        fileSearchResp.setFileType(fileInfo.getFileType());

        return fileSearchResp;
    }

    /**
     *
     * @param fileDetailReq
     * @return
     */

    public static FileInfo convertFileDetailReqToFileInfo(FileDetailReq fileDetailReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(fileDetailReq.getFileId());
        return fileInfo;
    }


    /**
     *
     * @param fileDetailReq
     * @return
     */
    public static AccessoryInfo convertFileDetailReqToAccessoryInfo(FileDetailReq fileDetailReq) {
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        accessoryInfo.setFileId(fileDetailReq.getFileId());
        return accessoryInfo;
    }

    /**
     *
     * @param fileInfo
     * @return
     */
    public static FileDetailResp convertFileInfoToFileDetailResp(FileInfo fileInfo) {
        FileDetailResp fileDetailResp = new FileDetailResp();
        fileDetailResp.setLabel(fileInfo.getLabel());
        fileDetailResp.setFileType(fileInfo.getFileType());
        fileDetailResp.setRemark(fileInfo.getRemark());
        fileDetailResp.setFileOwner(fileInfo.getFileOwner());
        fileDetailResp.setCreateDate(fileInfo.getCreateDate());
        fileDetailResp.setFilePath(fileInfo.getFilePath());
        return fileDetailResp;
    }



    /**
     * 将查询附件的请求类转为 FileInfo 类型
     * @param listAccessoryReq
     * @return
     */
    public static FileInfo convertListAccessoryReqToFileInfo(ListAccessoryReq listAccessoryReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(listAccessoryReq.getFileId());
        return fileInfo;

    }

    /**
     * 将查询文件请求类转换为 FileInfo 类型
     * @param listFileInfoReq
     * @return
     */

    public static FileInfo convertListFileInfoReqToFileInfo(ListFileInfoReq listFileInfoReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setParentId(listFileInfoReq.getParentId());
        return fileInfo;
    }

    /**
     * 将删除文件请求类转为 FileInfo 类型
     * @param deleteFileReq
     * @return
     */
    public static  FileInfo convertDeletedFileInfoToFileInfo(DeleteFileReq deleteFileReq) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(deleteFileReq.getFileId());
        fileInfo.setLabel(deleteFileReq.getFilePathAndName());
        return fileInfo;
    }


    /**
     * 将删除附件请求类转为 AccessoryInfo 类型
     * @param deleteAccessoryReq
     * @return
     */
    public static  AccessoryInfo convertDeletedAccessoryInfoToAccessoryInfo(DeleteAccessoryReq deleteAccessoryReq) {
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        accessoryInfo.setAccessoryId(deleteAccessoryReq.getAccessoryId());
        accessoryInfo.setAccessoryName(deleteAccessoryReq.getAccessoryPathAndName());
        return accessoryInfo;
    }


    public static User convertUserVoToEntity(UserEditVo userEditVo) {
        User user = new User();
        user.setUserId(userEditVo.getUserId());
        user.setUsername(userEditVo.getUsername());
        user.setPassword(userEditVo.getPassword());
        user.setChineseName(userEditVo.getChineseName());
        user.setDepartId(userEditVo.getDepartId());
        user.setGender(userEditVo.getGender());
        user.setRoleId(userEditVo.getRoleId());
        user.setUserStateId(userEditVo.getUserStateId());
        return user;
    }

    public static UserVo convertUserEntityToVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setPassword(user.getPassword());
        userVo.setChineseName(user.getChineseName());
        userVo.setDepartId(user.getDepartId());
        userVo.setGender(user.getGender());
        userVo.setRoleId(user.getRoleId());
        userVo.setUserStateId(user.getUserStateId());
        return userVo;
    }

    /**
     * @Description: 将Employee实体类中各属性的值对应赋给EmpListVo
     * @Classname : ConvertUtils
     * @author: Jason Jin
     * @date: 2019/6/23 12:01 PM
     */
    public static UserListVo convertUserEntityToListVo(User user) {
        UserListVo userListVo = new UserListVo();
        userListVo.setUserId(user.getUserId());
        userListVo.setUsername(user.getUsername());
        userListVo.setPassword(user.getPassword());
        userListVo.setChineseName(user.getChineseName());
        userListVo.setRoleId(user.getRoleId());
        return userListVo;
    }


    public static BranchListVo convertBranchEntityToListVo(Branch branch) {
        BranchListVo branchListVo = new BranchListVo();
        branchListVo.setBranchId(branch.getBranchId());
        branchListVo.setBranchName(branch.getBranchName());
        branchListVo.setBranchShortName(branch.getBranchShortName());
        return branchListVo;
    }

    public static DepartListVo convertDepartEntityToListVo(Depart depart) {
        DepartListVo departListVo = new DepartListVo();
        departListVo.setDepartId(depart.getDepartId());

        departListVo.setDepartName(depart.getDepartName());
        departListVo.setPrincipalUser(depart.getPrincipalUser());
        departListVo.setConnectTelNo(depart.getConnectTelNo());
        departListVo.setConnectMobileTelNo(depart.getConnectMobileTelNo());
        departListVo.setFaxes(depart.getFaxes());
        departListVo.setBranchId(depart.getBranchId());

        return departListVo;
    }


    public static Branch convertBranchVoToEntity(BranchEditVo branchEditVo) {
        Branch branch = new Branch();
        branch.setBranchId(branchEditVo.getBranchId());
        branch.setBranchName(branchEditVo.getBranchName());
        branch.setBranchShortName(branchEditVo.getBranchShortName());
        return branch;
    }

    public static Depart convertDepartVoToEntity(DepartEditVo departEditVo) {
        Depart depart = new Depart();
        depart.setDepartId(departEditVo.getDepartId());
        depart.setDepartName(departEditVo.getDepartName());
        depart.setPrincipalUser(departEditVo.getPrincipalUser());
        depart.setConnectTelNo(departEditVo.getConnectTelNo());
        depart.setConnectMobileTelNo(departEditVo.getConnectMobileTelNo());
        depart.setFaxes(departEditVo.getFaxes());
        depart.setBranchId(departEditVo.getBranchId());
        return depart;

    }

}
