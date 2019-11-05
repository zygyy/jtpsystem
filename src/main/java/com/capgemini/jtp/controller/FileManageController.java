package com.capgemini.jtp.controller;

import com.capgemini.jtp.service.FileManageService;
import com.capgemini.jtp.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api("文档管理")
@RequestMapping("/files")
public class FileManageController {
    @Autowired
    FileManageService fileManageService;
//    @ApiOperation(value = "三级菜单")
//    @ResponseBody
//    @RequestMapping(value = "/threemenu", method = RequestMethod.GET)
//    public RespBean threeMenu() {
//
//        return RespBean.ok(fileManageService.threeMenu());
//    }
    @ApiOperation(value = "n级菜单")
    @ResponseBody
//    @RequestMapping(value = "/nmenu", method = RequestMethod.POST)
    @GetMapping("/nmenu/{n}")
    public RespBean nMenu(@PathVariable("n") int n) {
        Object object=fileManageService.nMenu(n);
        if(object!=null) {
            return RespBean.ok(object);
        }else{
            return RespBean.error("菜单返回失败");
        }


    }
}
