package com.capgemini.jtp.controller;

import com.capgemini.jtp.service.MenuService;
import com.capgemini.jtp.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("菜单信息")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @ApiOperation("获取目录树")
    @RequestMapping("/menuTree")
    public RespBean getMenuTree() {

        return RespBean.ok(menuService.getTree());
    }
}
