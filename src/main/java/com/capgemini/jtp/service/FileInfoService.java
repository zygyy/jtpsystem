package com.capgemini.jtp.service;



import com.capgemini.jtp.entity.AccessoryInfo;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.FileDetailResp;
import com.capgemini.jtp.vo.response.FileSearchResp;
import com.capgemini.jtp.vo.response.ListFileInfoResp;
import com.capgemini.jtp.vo.response.ListRecycleBinResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileInfoService {
    public List<ListFileInfoResp> listCurrentDirDocument(ListFileInfoReq listFileInfoReq);

    public Integer editCurrentDocment(EditFileInfoReq editFileInfoReq);

    public List<AccessoryInfo> listAccessoryInfo(ListAccessoryReq listAccessoryReq);

    public Integer addFileToDb(AddFileReq addFileReq);

    public Integer addAccessoryToDb(AddAccessoryReq addAccessoryReq);

    public Boolean addFileToDesk(AddFileReq addFileReq);

    public Integer uploadFileToDb(UploadFileReq uploadFileReq);

    public Integer uploadFileToDisk(MultipartFile file, String accessoryPath);

    public Integer deleteFile(DeleteFileReq deleteFileReq);

    public Integer deleteAccessory(DeleteAccessoryReq deleteAccessoryReq);

    public Integer createFile(CreateFileReq addFileReq);

    public Integer createDir(CreateDirReq createDirReq);

    public Integer MoveFileToRecycleBin(MoveFileToRecycleBinReq moveFileToRecycleBinReq);

    public List<ListRecycleBinResp> listRecycleBin();

    public Integer fileReduction(FileReductionReq fileReductionReq);

    public List<FileSearchResp> fileSearch(FileSearchReq fileSearchReq);

    public FileDetailResp getFileDetail(FileDetailReq fileDetailReq);

    public Integer deleteFileFromDisk(String filePathAndName);


}
