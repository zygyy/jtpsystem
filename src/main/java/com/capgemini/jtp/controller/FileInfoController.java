package com.capgemini.jtp.controller;



import com.capgemini.jtp.entity.AccessoryInfo;
import com.capgemini.jtp.mapper.FileInfoMapper;
import com.capgemini.jtp.service.FileInfoService;
import com.capgemini.jtp.utils.FileUtils;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.FileDetailResp;
import com.capgemini.jtp.vo.response.FileSearchResp;
import com.capgemini.jtp.vo.response.ListFileInfoResp;
import com.capgemini.jtp.vo.response.ListRecycleBinResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:文件管理模块
 * create time: 13:18 2019/9/22
 */
@Api("文件管理模块")
@RestController
@RequestMapping("/file/filemanagement")
public class FileInfoController {
    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    FileInfoMapper fileInfoMapper;
    /**
     *查询当前目录下的所有文件
     * @param listFileInfoReq
     * @return
     */
    @RequestMapping(value="/listFile",method = RequestMethod.POST)
    @ApiOperation(value="列出当前目录下的所有文件")
    @ApiImplicitParam(paramType = "" , name = "listFile" , value = "列出文件类",dataType = "ListFileInfoVo")
    public RespBean listFile(@RequestBody ListFileInfoReq listFileInfoReq) {
        List<ListFileInfoResp> fileInfoResps = fileInfoService.listCurrentDirDocument(listFileInfoReq);
        if (fileInfoResps != null) {
            return RespBean.ok("查询成功",fileInfoResps);
        }else {
            return RespBean.error("查询失败");
        }
    }


    /**
     * 查询该文件的所有附件
     * @param listAccessoryReq
     * @return
     */
    @RequestMapping(value = "/listAccessory",method = RequestMethod.POST)
    @ApiOperation(value = "列出该文件的所有附件")
    @ApiImplicitParam(paramType = "",name = "listAccessory",value = "列出附件类",dataType = "ListAccessoryInfoVo")
    public RespBean listAccessory(@RequestBody ListAccessoryReq listAccessoryReq) {
        List<AccessoryInfo> accessoryInfos = fileInfoService.listAccessoryInfo(listAccessoryReq);
        return RespBean.ok(accessoryInfos);

    }


    /**
     * 编辑文件属性
     * @param editFileInfoReq
     * @return
     */
    @RequestMapping(value = "/editFile",method = RequestMethod.POST)
    @ApiOperation(value = "编辑当前文件属性")
    @ApiImplicitParam(paramType = "", name = "editFile", value = "编辑文件类",dataType = "EditFileInfoVo")
    public RespBean editFile(@RequestBody EditFileInfoReq editFileInfoReq) {

        if (fileInfoService.editCurrentDocment(editFileInfoReq) != 0) {
            return RespBean.ok("保存成功");
        }else {
            return RespBean.error("保存失败");
        }
    }
    
    
    
    /**
     * create by: MmmLll_Shen
     * description:新建文件夹或者文件
     * create time: 10:29 2019/9/23
     */
    @RequestMapping(value = "/addFile",method = RequestMethod.POST)
    @ApiOperation(value = "新建文件夹或者文件")
    public RespBean addFile(@RequestBody AddFileReq addFileReq) {
        System.out.println("----------------------------" + addFileReq.getFilePath());
//        if(fileInfoMapper.getFileTypeByFileId(addFileReq.getParentId()) != 1){
//            return RespBean.error("不能在此处新建文件夹或者文件");
//        }else {
        if (fileInfoService.addFileToDb(addFileReq) == 2){
            return RespBean.error("已经存在同名文件，创建失败");
        }
            if (fileInfoService.addFileToDb(addFileReq) != 0) {
                //在此新建文件到磁盘
                if(fileInfoService.addFileToDesk(addFileReq)){
                    return RespBean.okMessage("新建成功");
                }
                else {
                    return RespBean.okMessage("在磁盘中新建失败");
                }
            }else {
                return RespBean.error("新建失败");
            }
//        }
    }


    /**
     * create by: MmmLll_Shen
     * description:新建附件
     * create time: 10:29 2019/9/23
     */
    @RequestMapping(value = "/addAccessoryToDb",method = RequestMethod.POST)
    @ApiOperation(value = "新建附件（无fileId）")
    public RespBean addAccessoryToDb(@RequestBody AddAccessoryReq addAccessoryReq) {
//        System.out.println("----------------------------" + addFileReq.getFilePath());
//        if(fileInfoMapper.getFileTypeByFileId(addAccessoryReq.getFileId()) != 3){
//            return RespBean.error("不能在此处新建附件");
//        }else {
        addAccessoryReq.setFileId(fileInfoMapper.selectMaxId());
        if (fileInfoService.addAccessoryToDb(addAccessoryReq) == 2){
            return RespBean.error("已经存在同名文件，创建失败");
        }
            if (fileInfoService.addAccessoryToDb(addAccessoryReq) != 0) {
                //在此新建文件到磁盘
                    return RespBean.okMessage("新建成功");

            }else {
                return RespBean.error("新建失败");
            }
//        }
    }


    @RequestMapping(value = "/addAccessoryToDbHasFileId",method = RequestMethod.POST)
    @ApiOperation(value = "新建附件（有fileId）")
    public RespBean addAccessoryToDbHasFileId(@RequestBody AddAccessoryReq addAccessoryReq) {
//        System.out.println("----------------------------" + addFileReq.getFilePath());
//        if(fileInfoMapper.getFileTypeByFileId(addAccessoryReq.getFileId()) != 3){
//            return RespBean.error("不能在此处新建附件");
//        }else {
        if (fileInfoService.addAccessoryToDb(addAccessoryReq) == 2){
            return RespBean.error("已经存在同名文件，创建失败");
        }
        if (fileInfoService.addAccessoryToDb(addAccessoryReq) != 0) {
            //在此新建文件到磁盘
            return RespBean.okMessage("新建成功");

        }else {
            return RespBean.error("新建失败");
        }
//        }
    }


    /**
     * create by: MmmLll_Shen
     * description:上传文件到磁盘
     * create time: 11:31 2019/9/29
     */
    @RequestMapping(value = "/addAccessoryToDesk",method = RequestMethod.POST)
    @ApiOperation(value = "新建附件到磁盘")
    public RespBean addAccessoryToDesk( @RequestParam("file") MultipartFile file,String accessoryPath) {
        if (file.isEmpty()) {
            return RespBean.error("请选择文件");
        }
        if (fileInfoService.uploadFileToDisk(file,accessoryPath) != 0) {
            return RespBean.okMessage("上传成功");
        }else {
            return RespBean.error("上传失败");
        }
    }



    /**
     * 将文件放入回收站
     * @param moveFileToRecycleBinReq
     * @return
     */
    @RequestMapping(value = "/MoveFileToRecycleBin",method = RequestMethod.POST)
    @ApiOperation(value = "将文件放入回收站")
    @ApiImplicitParam(paramType = "", name = "MoveFileToRecycleBin",value = "将文件放入回收站",
            dataType = "MoveFileToRecycleBinReq")
    public RespBean MoveFileToRecycleBin(@RequestBody MoveFileToRecycleBinReq moveFileToRecycleBinReq) {
        if (fileInfoService.MoveFileToRecycleBin(moveFileToRecycleBinReq) != 0) {
            return RespBean.okMessage("操作成功");
        }else {
            return RespBean.error("操作失败");
        }
    }



    /**
     * 删除文件
     * @param deleteFileReq
     * @return
     */
    @RequestMapping(value = "/deleteFile",method = RequestMethod.POST)
    @ApiOperation(value = "删除文件")
    @ApiImplicitParam(paramType = "", name = "deleteFile",value = "删除文件",dataType = "DeleteFileReq")
    public RespBean deleteFile(@RequestBody DeleteFileReq deleteFileReq) {
        if (fileInfoService.deleteFile(deleteFileReq) != 0 &&
                fileInfoService.deleteFileFromDisk(deleteFileReq.getFilePathAndName()) != 0) {
            return RespBean.okMessage("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }


    /**
     * 查询回收站
     * @return
     */
    @RequestMapping(value = "/listRecycleBin",method = RequestMethod.POST)
    @ApiOperation(value = "查询回收站内容")
    @ApiImplicitParam(paramType = "",name = "listRecycleBin",value = "查询回收站内容",dataType = "listRecycleBinResp")
    public RespBean listRecycleBin() {
        List<ListRecycleBinResp> listRecycleBinResps = fileInfoService.listRecycleBin();

        return RespBean.ok("查询成功",listRecycleBinResps);
    }


    /**
     * 还原回收站文件
     * @param fileReductionReq
     * @return
     */
    @RequestMapping(value = "/fileReduction",method = RequestMethod.POST)
    @ApiOperation(value = "还原文件")
    @ApiImplicitParam(paramType = "",name = "fileReduction",value = "还原文件",dataType = "fileReductionVo")
    public RespBean fileReduction(@RequestBody FileReductionReq fileReductionReq) {
        if (fileInfoService.fileReduction(fileReductionReq) != 0) {
            return RespBean.okMessage("操作成功");
        }else {
            return RespBean.error("操作失败");
        }
    }

    /**
     * 文件搜索
     * @param fileSearchReq
     * @return
     */
    @RequestMapping(value = "/fileSearch",method = RequestMethod.POST)
    @ApiOperation(value = "文件搜索")
    @ApiImplicitParam(paramType = "",name = "fileSearch",value = "文件搜索",dataType = "fileSearchResp")
    public RespBean fileSearch(@RequestBody FileSearchReq fileSearchReq) {
        List<FileSearchResp> fileSearchResps = fileInfoService.fileSearch(fileSearchReq);

        return RespBean.ok("查询成功",fileSearchResps);
    }



    /**
     * 删除附件
     * @param deleteAccessoryReq
     * @return
     */
    @RequestMapping(value = "/deleteAccessory",method = RequestMethod.POST)
    @ApiOperation(value = "删除附件")
    public RespBean deleteFile(@RequestBody DeleteAccessoryReq deleteAccessoryReq) {
        if (fileInfoService.deleteFileFromDisk(fileInfoMapper.getAccessoryPathByAccessoryId(deleteAccessoryReq.getAccessoryId())) != 0
            && fileInfoService.deleteAccessory(deleteAccessoryReq) != 0) {
            return RespBean.okMessage("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }


    /**
     * 查询文件详情
     * @param fileDetailReq
     * @return
     */
    @RequestMapping(value = "/fileDetail",method = RequestMethod.POST)
    @ApiOperation(value = "文件详情")
    @ApiImplicitParam(paramType = "",name = "fileDetail",value = "文件详情",dataType = "fileDetailResp")
    public RespBean getFileDetail(@RequestBody FileDetailReq fileDetailReq) {
        FileDetailResp fileDetailResp = fileInfoService.getFileDetail(fileDetailReq);

        return RespBean.ok("查询成功",fileDetailResp);
    }
    
    
    /**
     * create by: MmmLll_Shen
     * description:退出按钮（编辑时的退出按钮）
     * create time: 11:03 2019/10/9
     */
    @RequestMapping(value = "/exit",method = RequestMethod.POST)
    @ApiOperation(value = "退出按钮：只删除磁盘中的附件")
    public RespBean exit(@RequestBody DeleteAccessoryReq deleteAccessoryReq) {
        if (fileInfoService.deleteFileFromDisk(deleteAccessoryReq.getAccessoryPathAndName()) != 0) {
            return RespBean.okMessage("退出成功");
        }else {
            return RespBean.error("退出失败");
        }
    }



    /**
     * create by: MmmLll_Shen
     * description:退出按钮（创建时的退出按钮）
     * create time: 11:03 2019/10/9
     */
    @RequestMapping(value = "/exitTwo",method = RequestMethod.POST)
    @ApiOperation(value = "退出按钮：删除磁盘中的文件和数据库中的文件")
    public RespBean exitTwo(@RequestBody DeleteFileAndAccessoryReq deleteFileAndAccessoryReq) {
        //首先判断有没有在数据库库中创建文件
        if(fileInfoMapper.getFileIdByPath(deleteFileAndAccessoryReq.getFilePathAndName()) != null && fileInfoMapper.getFileIdByPath(deleteFileAndAccessoryReq.getFilePathAndName()) != 0 ){
            if (fileInfoService.deleteFileFromDisk(deleteFileAndAccessoryReq.getAccessoryPathAndName()) != 0
            && fileInfoService.deleteFileFromDisk(deleteFileAndAccessoryReq.getFilePathAndName()) != 0
            && fileInfoMapper.deleteFileByPath(deleteFileAndAccessoryReq.getFilePathAndName()) != 0) {
                return RespBean.okMessage("退出成功");
            }else {
                return RespBean.error("退出失败");
            }
        }
        else {
            return RespBean.ok("退出成功");
        }
    }



    /**
     * create by: MmmLll_Shen
     * description:新建文件及附件
     * create time: 10:39 2019/10/10
     */
    @RequestMapping(value = "/addAccessoryToDeskAndFileToDb",method = RequestMethod.POST)
    @ApiOperation(value = "新建文件到数据库及附件到磁盘(实验接口，切勿使用)")
    public RespBean addAccessoryToDeskAndFileToDb(FileAndAccessoryReq fileAndAccessoryReq) {
        //先创建文件


        //首先测试创建附件可不可以（创建路径+file）
        if (fileAndAccessoryReq.getMultipartFile().isEmpty()) {
            return RespBean.error("请选择文件");
        }
        if (fileInfoService.uploadFileToDisk(fileAndAccessoryReq.getMultipartFile(),fileAndAccessoryReq.getAccessoryPath()) != 0) {
            return RespBean.okMessage("上传成功");
        }else {
            return RespBean.error("上传失败");
        }
    }


    /**
     * create by: MmmLll_Shen
     * description:下载文件
     * create time: 16:15 2019/10/10
     */
    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    @ApiOperation(value = "下载文件")
    public void testDownload(HttpServletResponse res,String filePath) throws UnsupportedEncodingException {
        String[] strArr = filePath.split("\\\\");
        System.out.println("-------------------------" + strArr[strArr.length - 1]);

        String file = strArr[strArr.length - 1];
        String[] strArr2 = file.split("\\.");
        String file2 = strArr2[strArr2.length - 1];

        StringBuilder fileName = new StringBuilder();
        fileName.append(String.valueOf("."));
        fileName.append(String.valueOf(file2));

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream;charset=utf-8");
        res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName.toString(),"utf-8"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }



}
