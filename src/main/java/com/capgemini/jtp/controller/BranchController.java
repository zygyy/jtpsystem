package com.capgemini.jtp.controller;

import com.capgemini.jtp.service.BranchService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.BranchDeleteVo;
import com.capgemini.jtp.vo.request.BranchEditVo;
import com.capgemini.jtp.vo.response.BranchListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api("机构信息类")
@RestController
@RequestMapping("/home/branchmessage")
public class BranchController {
    @Autowired
    BranchService branchService;

    @ApiOperation(value = "添加机构信息")
    @ResponseBody
    @RequestMapping(value = "/addBranch", method = RequestMethod.POST)
    public RespBean addBranch(@Valid @RequestBody BranchEditVo branchEditVo, HttpServletRequest request) {
        int status = branchService.addBranch(branchEditVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("重复编号！");
            }
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(value = "编辑机构信息")
    @ResponseBody
    @RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
    public RespBean updateBranch(@Valid @RequestBody BranchEditVo branchEditVo, HttpServletRequest request) {
        int status = branchService.updateBranch(branchEditVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.okMessage("更新成功！");
        }
        return RespBean.error("更新失败!");
    }


    @ApiOperation(value = "机构List")
    @ResponseBody
    @RequestMapping(value = "/listBranchVo", method = RequestMethod.POST)
    public RespBean listBranchVo() {
        List<BranchListVo> branchListVos = branchService.listBranchVo();
        if (branchListVos != null) {
            return RespBean.ok(branchListVos);
        }
        return RespBean.error("查询失败！");
    }


    @ApiOperation(value = "删除机构信息")
    @ResponseBody
    @RequestMapping(value = "/deleteBranchByBranchId", method = RequestMethod.POST)
    public RespBean deleteBranchByBranchId(@RequestBody BranchDeleteVo branchDeleteVo, HttpServletRequest request) {
        int status = branchService.deleteBranchByBranchId(branchDeleteVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }

}


