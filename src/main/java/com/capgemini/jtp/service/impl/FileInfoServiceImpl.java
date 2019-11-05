package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.entity.AccessoryInfo;
import com.capgemini.jtp.entity.FileInfo;
import com.capgemini.jtp.entity.FileTypeInfo;
import com.capgemini.jtp.mapper.FileInfoMapper;
import com.capgemini.jtp.service.FileInfoService;
import com.capgemini.jtp.utils.ConverLog;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.utils.FileUtils;
import com.capgemini.jtp.utils.TimeFrame;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.FileDetailResp;
import com.capgemini.jtp.vo.response.FileSearchResp;
import com.capgemini.jtp.vo.response.ListFileInfoResp;
import com.capgemini.jtp.vo.response.ListRecycleBinResp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Folder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * create by: MmmLll_Shen
 * description:
 * create time: 13:45 2019/9/22
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    FileInfoMapper fileInfoMapper;

    /**
     * 列出当前目录下的所有文件
     * @param listFileInfoReq
     * @return
     */
    @Override
    public List<ListFileInfoResp> listCurrentDirDocument(ListFileInfoReq listFileInfoReq) {
        FileInfo fileInfoo = new FileInfo();
        fileInfoo = ConvertUtils.convertListFileInfoReqToFileInfo(listFileInfoReq);
        List<FileInfo> fileInfos = fileInfoMapper.listCurrentDirDocument(fileInfoo);
        List<ListFileInfoResp> listFileInfoResps = new ArrayList<>();

        if(fileInfos != null) {
            for (FileInfo fileInfo : fileInfos) {
                ListFileInfoResp listFileInfoResp = ConvertUtils.convertFileInfoToListFileInfoResp(fileInfo);
                listFileInfoResp.setFileTypeName(fileInfoMapper.getFileTypeName(fileInfo.getFileType()));
                listFileInfoResps.add(listFileInfoResp);
            }
        }
        return listFileInfoResps;
    }

    /**
     * 编辑当前文件属性
     * @param editFileInfoReq
     * @return
     */
    @Override
    public Integer editCurrentDocment(EditFileInfoReq editFileInfoReq) {
        FileInfo fileInfo = ConvertUtils.convertEditFileInfoReqToFileInfo(editFileInfoReq);
//        FileTypeInfo fileTypeInfo = ConvertUtils.convertEditFileInfoReqToFileTypeInfo(editFileInfoReq);
        if (fileInfoMapper.updateFileInfoById(fileInfo) != 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * 列出该文件的所有附件信息
     * @param listAccessoryReq
     * @return
     */
    @Override
    public List<AccessoryInfo> listAccessoryInfo(ListAccessoryReq listAccessoryReq) {
        FileInfo fileInfo = ConvertUtils.convertListAccessoryReqToFileInfo(listAccessoryReq);
        List<AccessoryInfo> accessoryInfoList = fileInfoMapper.listAccessoryInfo(fileInfo);
        for(AccessoryInfo accessoryInfo : accessoryInfoList){
            accessoryInfo.setAccessoryTypeName(fileInfoMapper.getAccessoryTypeName(accessoryInfo.getAccessoryType()));
        }
        return accessoryInfoList;

    }


    /**
     * 新建文件信息到数据库
     * @return
     */
    @Override
    public Integer addFileToDb(AddFileReq addFileReq) {
        if(fileInfoMapper.getFileIdByLabel(addFileReq.getLabel()) != null){
            return 2;
        }
        FileInfo fileInfo = ConvertUtils.convertAddFileInfoToFileInfo(addFileReq);
        StringBuilder filePath = new StringBuilder();
        filePath.append(fileInfoMapper.getFilePathByFileId(addFileReq.getParentId()).toString());
        filePath.append('\\');
        filePath.append('\\');
        filePath.append(addFileReq.getLabel());
        fileInfo.setFilePath(filePath.toString());
        return fileInfoMapper.addFileInfo(fileInfo);
    }


    /**
     * create by: MmmLll_Shen
     * description:新建附件到数据库
     * create time: 16:48 2019/9/29
     */
    @Override
    public Integer addAccessoryToDb(AddAccessoryReq addAccessoryReq) {
        if (fileInfoMapper.getAccessoryIdByAccessoryName(addAccessoryReq.getAccessoryName()) != null){
            return 2;
        }
        AccessoryInfo accessoryInfo = ConvertUtils.convertAddAccessoryInfoToAccessoryInfo(addAccessoryReq);
        StringBuilder filePath = new StringBuilder();
        filePath.append(fileInfoMapper.getFilePathByFileId(addAccessoryReq.getFileId()).toString());
        filePath.append('\\');
        filePath.append('\\');
        filePath.append(addAccessoryReq.getAccessoryName());
        accessoryInfo.setAccessoryPath(filePath.toString());
        return fileInfoMapper.addAccessoryInfo(accessoryInfo);
    }

   /**
    * create by: MmmLll_Shen
    * description:新建文件到磁盘
    * create time: 14:47 2019/9/29
    */
    @Override
    public Boolean addFileToDesk(AddFileReq addFileReq) {
        FileInfo fileInfo = ConvertUtils.convertAddFileInfoToFileInfo(addFileReq);
        StringBuilder filePath = new StringBuilder();
        filePath.append(fileInfoMapper.getFilePathByFileId(addFileReq.getParentId()).toString());
        filePath.append('\\');
        filePath.append('\\');
        filePath.append(addFileReq.getLabel());
        fileInfo.setFilePath(filePath.toString());
//        String filePath = fileInfoMapper.getFilePathByFileId(addFileReq.getParentId()) + "\\" + "\\" + addFileReq.getFileName();
        File file=new File(filePath.toString()); //假设你要创建的目录是 d:/xxx


        return file.mkdir();

    }

    /**
     * 上传文件信息到数据库
     * @param uploadFileReq
     * @return
     */
    @Override
    public Integer uploadFileToDb(UploadFileReq uploadFileReq) {
        FileInfo fileInfo = ConvertUtils.convertUploadFileInfoToFileInfo(uploadFileReq);

        fileInfoMapper.addFileInfo(fileInfo);
        //获取插入的fileInfo 的 fileId
        int fileId = fileInfoMapper.getLastInsertId();
        uploadFileReq.setFileId(fileId);
        AccessoryInfo accessoryInfo = ConvertUtils.convertUploadFileInfoToAccessoryInfo(uploadFileReq);

        return fileInfoMapper.addAccessoryInfo(accessoryInfo);
    }


    /**
     * 上传附件到磁盘
     * @param file
     * @return
     */
    @Override
    public Integer uploadFileToDisk(MultipartFile file, String accessoryPath) {
        String fileName = file.getOriginalFilename();
        File dest = new File(accessoryPath);
        try {
            file.transferTo(dest);
            return 1;

        } catch (IOException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    /**
     * 删除文件
     * @param deleteFileReq
     * @return
     */

    @Override
    public Integer deleteFile(DeleteFileReq deleteFileReq) {

        FileInfo fileInfo = ConvertUtils.convertDeletedFileInfoToFileInfo(deleteFileReq);
        return fileInfoMapper.deleteFile(fileInfo);
    }

    /**
     * 从磁盘删除文件
     * @param filePathAndName
     * @return
     */

    @Override
    public Integer deleteFileFromDisk(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();
            return 1;
        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 删除附件
     * @param deleteAccessoryReq
     * @return
     */

    @Override
    public Integer deleteAccessory(DeleteAccessoryReq deleteAccessoryReq) {

        AccessoryInfo accessoryInfo = ConvertUtils.convertDeletedAccessoryInfoToAccessoryInfo(deleteAccessoryReq);
        return fileInfoMapper.deleteAccessory(accessoryInfo);
    }

//    /**
//     * 从磁盘删除附件
//     * @param filePathAndName
//     * @return
//     */
//
//    @Override
//    public Integer deleteAccessoryFromDisk(String filePathAndName) {
//        try {
//            String filePath = filePathAndName;
//            filePath = filePath.toString();
//            File myDelFile = new File(filePath);
//            myDelFile.delete();
//            return 1;
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return 0;
//        }
//    }










    /**
     * 新建文件
     * @param addFileReq
     * @return
     */

    @Override
    public Integer createFile(CreateFileReq addFileReq) {
        FileInfo fileInfo = ConvertUtils.convertCreateFileInfoToFileInfo(addFileReq);
        File file = new File(fileInfo.getFilePath() + "\\" + fileInfo.getLabel());

        try {
            file.createNewFile();
            System.out.println(file.getPath());
        }catch (IOException e)  {
            return -1;
        }
        return fileInfoMapper.addFileInfo(fileInfo);

    }

    /**
     * 新建目录
     * @param createDirReq
     * @return
     */

    @Override
    public Integer createDir(CreateDirReq createDirReq) {
        FileInfo fileInfo = ConvertUtils.convertCreateDirInfoToFileInfo(createDirReq);
        File file = new File(createDirReq.getFilePath() + "\\" + createDirReq.getFileName());
        file.mkdirs();

        return fileInfoMapper.addFileInfo(fileInfo);
    }

    /**
     * 将文件放入回收站
     * @param moveFileToRecycleBinReq
     * @return
     */

    @Override
    public Integer MoveFileToRecycleBin(MoveFileToRecycleBinReq moveFileToRecycleBinReq) {
        FileInfo fileInfo = ConvertUtils.convertMoveFileToRecycleBinReqToFileInfo(moveFileToRecycleBinReq);

        return fileInfoMapper.MoveFileToRecycleBin(fileInfo);
    }


    /**
     * 查询回收站文件
     * @return
     */
    @Override
    public List<ListRecycleBinResp> listRecycleBin() {
        List<FileInfo> fileInfos = fileInfoMapper.listRecycleBin();
        List<ListRecycleBinResp> listFileInfoResps = new ArrayList<>();
        ListRecycleBinResp listRecycleBinResp = new ListRecycleBinResp();
        for (FileInfo fileInfo : fileInfos) {
            listRecycleBinResp = ConvertUtils.convertFileInfoToListRecycleBinResp(fileInfo);
            listRecycleBinResp.setFileTypeName(fileInfoMapper.getFileTypeName(fileInfo.getFileType()));
            listFileInfoResps.add(listRecycleBinResp);
        }

        return listFileInfoResps;

    }


    /**
     * 还原回收站中的文件
     * @param fileReductionReq
     * @return
     */
    @Override
    public Integer fileReduction(FileReductionReq fileReductionReq) {
        FileInfo fileInfo = ConvertUtils.convertFileReductionReqToFileInfo(fileReductionReq);

        return fileInfoMapper.fileReduction(fileInfo);
    }



    /**
     * create by: MmmLll_Shen
     * description:文件搜索
     * create time: 9:37 2019/9/30
     */
    @Override
    public List<FileSearchResp> fileSearch(FileSearchReq fileSearchReq) {
        List<FileSearchResp> fileSearchRespArrayList = new ArrayList<>();
        Integer choose = fileSearchReq.getLimit();
        if (choose != null) {
            Date now = new Date();
            if (choose == 1) {
                fileSearchReq.setStartDate(TimeFrame.startOfDay(now));
                fileSearchReq.setEndDate(TimeFrame.endOfDay(now));
            } else if (choose == 2) {
                fileSearchReq.setStartDate(TimeFrame.firstDateOfWeek(now));
                fileSearchReq.setEndDate(TimeFrame.lastDateOfWeek(now));
            } else if (choose == 3) {
                fileSearchReq.setStartDate(TimeFrame.firstDateOfWonth(now));
                fileSearchReq.setEndDate(TimeFrame.lastDateOfMonth(now));
            }
        }
        List<FileInfo> fileInfoList = fileInfoMapper.fileSearch(fileSearchReq);
        for (FileInfo fileInfo : fileInfoList) {
            fileSearchRespArrayList.add(ConverLog.convertFileInfoToFileSearchResp(fileInfo));
        }

        return fileSearchRespArrayList;
    }


    /**
     * 获取文件详情
     * @param fileDetailReq
     * @return
     */
    @Override
    public FileDetailResp getFileDetail(FileDetailReq fileDetailReq) {
        FileInfo fileInfo = ConvertUtils.convertFileDetailReqToFileInfo(fileDetailReq);

        FileDetailResp fileDetailResp =
                ConvertUtils.convertFileInfoToFileDetailResp(fileInfoMapper.getFileDetail(fileInfo));
//
//        AccessoryInfo accessoryInfo = ConvertUtils.convertFileDetailReqToAccessoryInfo(fileDetailReq);
//
//        fileDetailResp.setAccessorySize(fileInfoMapper.getAccessorySize(accessoryInfo));
        return fileDetailResp;
    }




}
