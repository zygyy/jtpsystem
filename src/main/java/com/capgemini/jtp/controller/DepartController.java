package com.capgemini.jtp.controller;

import com.capgemini.jtp.service.DepartService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.DepartDeleteVo;
import com.capgemini.jtp.vo.request.DepartEditVo;
import com.capgemini.jtp.vo.response.DepartListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api("部门信息")
@RestController
@RequestMapping("/home/departmessage")
public class DepartController {
    @Autowired
    DepartService departService;

    @ApiOperation(value = "添加部门信息")
    @ResponseBody
    @RequestMapping(value = "/addDepart", method = RequestMethod.POST)
    public RespBean addEmp(@Valid @RequestBody DepartEditVo departEditVo, HttpServletRequest request) {
        int status = departService.addDepart(departEditVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("重复工号！");
            }
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(value = "编辑部门信息")
    @ResponseBody
    @RequestMapping(value = "/updateDepart", method = RequestMethod.POST)
    public RespBean updateDepart(@Valid @RequestBody DepartEditVo departEditVo, HttpServletRequest request) {
        int status = departService.updateDepart(departEditVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.okMessage("更新成功！");
        }
        return RespBean.error("更新失败!");
    }


    @ApiOperation(value = "部门List")
    @ResponseBody
    @RequestMapping(value = "/listDepartVo", method = RequestMethod.POST)
    public RespBean listDepartVo() {
        List<DepartListVo> departListVos = departService.listDepartVo();
        if (departListVos != null) {
            return RespBean.ok(departListVos);
        }
        return RespBean.error("查询失败！");
    }


    @ApiOperation(value = "删除部门信息")
    @ResponseBody
    @RequestMapping(value = "/deleteDepartByDepartId", method = RequestMethod.POST)
    public RespBean deleteDepartByDepartId(@RequestBody DepartDeleteVo departDeleteVo, HttpServletRequest request) {
        int status = departService.deleteDepartByDepartId(departDeleteVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

}
