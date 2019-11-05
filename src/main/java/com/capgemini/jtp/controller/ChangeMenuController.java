package com.capgemini.jtp.controller;

//import com.capgemini.jtp.entity.Employee;
//import com.capgemini.jtp.service.StaffService;

import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.mapper.ChangeMenuMapper;
import com.capgemini.jtp.service.ChangeMenuService;
import com.capgemini.jtp.vo.base.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("菜单排序操作")
@RestController
@RequestMapping("/menu")
public class ChangeMenuController {


    @Autowired
    ChangeMenuService changeMenuService;

    @Autowired
    ChangeMenuMapper changeMenuMapper;

//    @Autowired
//    private StaffService staffService;

    @ApiOperation(value = "菜单上移操作")
    @PostMapping("/upMenu")
    public RespBean upMenu(@RequestBody Menu menu) {
        menu.setParentNodeId(changeMenuMapper.getParentIdByNodeId(menu.getNodeId()));
        menu.setDisplayOrder(changeMenuMapper.getDisplayOrderBynodeId(menu.getNodeId()));
        //先进行判断能否进行上移
        if (changeMenuService.isCanUpMenu(menu)) {
            int result = changeMenuService.upMenu(menu);
            if (result == 1) {
                return RespBean.ok("上移成功");
            } else {
                return RespBean.error("上移失败");
            }
        } else {
            return RespBean.error("不能上移");
        }
    }

    @ApiOperation(value = "菜单下移操作")
    @PostMapping("/doneMenu")
    public RespBean doneMenu(@RequestBody Menu menu) {
        menu.setParentNodeId(changeMenuMapper.getParentIdByNodeId(menu.getNodeId()));
        menu.setDisplayOrder(changeMenuMapper.getDisplayOrderBynodeId(menu.getNodeId()));
        System.out.println("-1----------------" + menu.getParentNodeId());
        //先进行判断能否进行下移
        if (changeMenuService.isCanDoneMenu(menu)) {
            System.out.println("0----------------" + menu.getParentNodeId());
            int result = changeMenuService.doneMenu(menu);
            if (result == 1) {
                return RespBean.ok("下移成功");
            } else {
                return RespBean.error("下移失败");
            }
        } else {
            return RespBean.error("不能下移");
        }
    }
//
//
////        int result = menuService.upMenu(menu.getNodeId());
////        System.out.println("-------------------" + result);
////        List<Menu> menuList = menuService.getMenusByHrId();
////        return menuList;
//    }
//    public RespBean getEmpByEmpId(@PathVariable("empId") Long empId) {
//        Employee employee =  staffService.getEmpByEmpId(empId);
//        if (employee != null) {
//            return RespBean.ok(employee);
//        } else {
//            return RespBean.error("员工id有误！");
//        }
//    }
//
//    @ApiOperation(value = "菜单下移操作")
//    @PostMapping("/getEmpBy/{empId}")
//    public RespBean getEmpByEmpId(@PathVariable("empId") Long empId) {
//        Employee employee =  staffService.getEmpByEmpId(empId);
//        if (employee != null) {
//            return RespBean.ok(employee);
//        } else {
//            return RespBean.error("员工id有误！");
//        }
//    }
}
