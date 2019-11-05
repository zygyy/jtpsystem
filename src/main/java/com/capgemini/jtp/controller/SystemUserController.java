package com.capgemini.jtp.controller;

import com.capgemini.jtp.service.UserService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.UserEditVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 10:13 2019/9/20
 */
@Api("系统用户账户设置")
@RestController
@RequestMapping("/system/user")
public class SystemUserController {

    @Autowired
    UserService userService;


    @ApiOperation("用户修改密码")
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RespBean updateHr(@RequestBody UserEditVo userEditVo) {
        if (userService.updateUser(userEditVo) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }





}
