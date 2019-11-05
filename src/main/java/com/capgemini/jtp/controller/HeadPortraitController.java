package com.capgemini.jtp.controller;

import com.capgemini.jtp.mapper.UserMapper;
import com.capgemini.jtp.service.HeadPortraitService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.UserNameVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;

@RestController
@Api("头像")
@RequestMapping("/HeadPortrait")
public class HeadPortraitController {
    @Autowired
    HeadPortraitService headPortraitService;

    @Autowired
    UserMapper userMapper;
    @ApiOperation(value = "上传头像")
    @ResponseBody
    @RequestMapping(value = "/headUpload", method = RequestMethod.POST)
    public RespBean headUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request)  {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        int state =0;
        try {
           state= headPortraitService.headUpload(file, request);

        }catch (IOException e){
            e.getMessage();
        }
        if(state>-1){
            if(state==0){
                return RespBean.okMessage("上传成功,水印失败");
            }else
            return RespBean.okMessage("成功");

        }else {
            return RespBean.error("上传失败");
        }

    }

    @ApiOperation(value = "显示头像")
    @ResponseBody
    @RequestMapping(value = "/getHeadUrl", method = RequestMethod.POST)
    public RespBean getHeadUrl(@Valid @RequestBody UserNameVo  userNameVo, HttpServletRequest request) throws IOException{

        String  string =headPortraitService.getHeadUrl(userNameVo.getUserName(),request);
        if(string==null)
        {
            return RespBean.error("没有找到相应头像");
        }else {
            return RespBean.ok(string);
        }
    }
    @ApiOperation(value = "头像1")
    @ResponseBody
    @RequestMapping(value = "/getImg", method = RequestMethod.GET)
    public void getFile(@RequestParam("url")String url,HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        headPortraitService.getImg(url,request,response);


    }
    @ApiOperation(value = "头像2")
    @ResponseBody
    @RequestMapping(value = "/getImgs", method = RequestMethod.GET)
    public void getFile(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        headPortraitService.getImgs(request,response);
    }



}
