package com.capgemini.jtp.controller;

import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.entity.User;
//import Menu;
import com.capgemini.jtp.common.UserUtils;
import com.capgemini.jtp.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description: 这是一个只要登录就能访问的Controller 主要用来获取一些配置信息
 * @Classname : ConfigController
 * @author: Jason Jin
 * @date: 2019/5/19 11:45 PM
 */
@Api("动态菜单")
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    MenuService menuService;

    @ApiOperation(value = "菜单显示")
    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu() {
        return menuService.getMenusByHrId();
    }

    @ApiOperation(value = "获取当前用户的Id")
    @RequestMapping("/hr")
    public User currentUser() {
        return UserUtils.getCurrentUser();
    }
}
