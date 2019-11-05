package com.capgemini.jtp.controller;

import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.DeleteBatchVo;
import com.capgemini.jtp.vo.request.OperateLogReq;
import com.capgemini.jtp.vo.response.OperateLogResp;
import com.capgemini.jtp.service.OperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:操作日志
 * create time: 13:14 2019/9/21
 */
@Api("操作日志")
@RestController
@RequestMapping("/system/operatelog")
public class OperateLogController {

    @Autowired
    OperateLogService operateLogService;

    /**
     * 根据操作日志的 id 删除操作日志
     * @param operateId
     * @return
     */
    @ApiOperation(value = "根据操作日志的 id 删除操作日志")
    @DeleteMapping("/deleteoperatelogbyid/{operateId}")
    public String deleteOperateLogByOperateLogId(@PathVariable Integer operateId) {
        int result = operateLogService.deleteByPrimaryKey(operateId);
        if (result != 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    /**
     * 查询所有的操作日志
     * @return
     */
    @ApiOperation(value = "查询所有的操作日志")
    @PostMapping("/selectalloperatelog")
    public RespBean loadAllOperateLog(@RequestBody OperateLogReq operateLogReq) {
        List<OperateLogResp> operateLogRespList = new ArrayList<>();
        operateLogRespList =  operateLogService.selectAllOperateLog(operateLogReq);
        if(operateLogRespList != null){
            return RespBean.ok(operateLogRespList);
        }else {
            return RespBean.error("查询失败");
        }
    }


    @ApiOperation(value = "批量删除操作日志信息")
    @RequestMapping(value = "/deleteOperateLoginLogs", method = RequestMethod.POST)
    public RespBean deleteBatchExp(@RequestBody DeleteBatchVo deleteBatchVo) {
        if (operateLogService.deleteOperateLogBatch(deleteBatchVo) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
