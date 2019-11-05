package com.capgemini.jtp.controller;

import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.service.LoginLogService;
import com.capgemini.jtp.service.RoleInfoService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.LoginLogResp;
import com.capgemini.jtp.vo.response.RoleListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("系统登录日志操作")
@RestController
@RequestMapping("/system/loginlog")
public class SystemLoginLogController {

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    RoleInfoService roleInfoService;
    /**
     * 根据指定的时间范围查询登录日志信息
     * @Date: 2019-09-02
     * @param loadLoginLogReq
     * @return
     */
    @ApiOperation("加载日志信息")
    @PostMapping("/loadLoginLog")
    public RespBean loadUserLoginLog(@RequestBody LoadLoginLogReq loadLoginLogReq) {
        List<LoginLogResp> loginLogs = loginLogService.selectLoginLog(loadLoginLogReq);
        if(loginLogs != null){
            return RespBean.ok(loginLogs);
        }else {
            return RespBean.error("查询失败！");
        }

    }

    /**
     * 根据登录日志 id 删除登录日志
     * @param loginLogId
     * @return
     */
    @DeleteMapping("/deleteLoginLogById/{loginLogId}")
    public Integer deleteLoginLogById(@PathVariable("loginLogId") Integer loginLogId) {
        return loginLogService.deleteByPrimaryKey(loginLogId);
    }



    @ApiOperation(value = "批量删除登录日志信息")
    @RequestMapping(value = "/deleteBatchLoginLogs", method = RequestMethod.POST)
    public RespBean deleteBatchExp(@RequestBody DeleteBatchVo deleteBatchVo) {
        if (loginLogService.deleteLoginLofBatch(deleteBatchVo) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    /**
     * 角色管理
     * @Date: 2019-09-02

     * @return
     */
 @ApiOperation(value="查询所有角色信息")
    @RequestMapping(value = "/roleList",method = RequestMethod.POST)
    public RespBean listRoleInfo(){
      List<RoleListVo> roleListVoList=roleInfoService.roleInfolist();
     return RespBean.ok(roleListVoList);
 }


    @ApiOperation(value="修改所有角色信息")
    @RequestMapping(value = "/roleList/update",method = RequestMethod.POST)
    public RespBean UpdateRoleById(@RequestBody RoleAddVo roleAddVo){
        if(roleInfoService.updateRole(roleAddVo)!=0)
            return RespBean.okMessage("角色修改成功");
        else
            return RespBean.error("角色修改失败");

    }



    @ApiOperation(value = "删除角色")
    @ResponseBody
    @RequestMapping(value = "/roleList/delete", method = RequestMethod.POST)
    public RespBean deleteNote(@Valid @RequestBody RoleDeleteVo roleDeleteVo) {
        if(roleInfoService.deleteRole(roleDeleteVo.getRoleId())!=0)
            return RespBean.okMessage("角色删除成功");
        else
            return RespBean.error("角色删除失败");
    }


    @ApiOperation(value="添加角色信息")
    @RequestMapping(value = "/roleList/add",method = RequestMethod.POST)
    public RespBean addRoleById(@RequestBody RoleAddVo roleAddVo){
        if(roleInfoService.addRole(roleAddVo)!=0)
            return RespBean.okMessage("角色添加成功");
        else
            return RespBean.error("角色添加失败");

    }

    @ApiOperation(value="分配角色权限")
    @RequestMapping(value = "/roleList/addPower",method = RequestMethod.POST)
    public RespBean addPowerById(@RequestBody AddPowerVo addPowerVo) {
        if (roleInfoService.addPowerRole(addPowerVo) == 0)
                return RespBean.error("分配失败");


            else
                return RespBean.okMessage("分配成功");


        }
    }