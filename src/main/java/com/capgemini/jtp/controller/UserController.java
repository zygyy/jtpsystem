package com.capgemini.jtp.controller;

import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.mapper.RoleMapper;
import com.capgemini.jtp.service.UserService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.UpdatePasswordVo;
import com.capgemini.jtp.vo.request.UserDeleteVo;
import com.capgemini.jtp.vo.request.UserEditVo;
import com.capgemini.jtp.vo.request.UserMassageVo;
import com.capgemini.jtp.vo.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Api("员工基本类")
@RestController
@RequestMapping("/home/usermessage")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleMapper roleMapper;




    @ApiOperation(value = "添加用户")
    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RespBean addUser(@Valid @RequestBody UserEditVo userEditVo, HttpServletRequest request) {
        int status = userService.addUser(userEditVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("重复用户！");
            }
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败!");
    }


    @ApiOperation(value = "员工List")
    @ResponseBody
    @RequestMapping(value = "/listUserVo", method = RequestMethod.POST)
    public RespBean listUserVo() {
        List<UserMassageRespVo> userListVos = userService.userlistMassage();
        if (userListVos != null) {
            return RespBean.ok("查询成功",userListVos);
        }
        return RespBean.error("查询失败！");
    }
    @ApiOperation(value = "员工详细信息")
    @ResponseBody
    @RequestMapping(value = "/userMassage", method = RequestMethod.POST)
    public RespBean userMassage(@Valid @RequestBody UserMassageVo userMassageVo) {
        UserMassageRespVo userMassageRespVo = userService.userMassage(userMassageVo);

        if (userMassageRespVo != null) {
            return RespBean.ok(userMassageRespVo);
        }
        return RespBean.error("查询失败！");
    }
//    /**
//     * @Title: updateEmp
//     * @Description: 根据数据库主键ID更新员工信息
//     * @Param: [employeeVo]
//     * @Return: com.capgemini.jtp.vo.base.RespBean
//     * @Throws:
//     * @Author: Brady
//     * @Date: 5/30/2019 11:57 AM
//     */
    @ApiOperation(value = "编辑员工信息")
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public RespBean updateUser(@Valid @RequestBody UserEditVo userEditVo, HttpServletRequest request) {
        int status = userService.updateUser(userEditVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.okMessage("更新成功！");
        }
        return RespBean.error("更新失败!");
    }

//
//    /**
//     * @Title: deleteEmpById
//     * @Description: 根据主键ID删除员工信息
//     * @Param: [baseVo]
//     * @Return: com.capgemini.jtp.vo.base.RespBean
//     * @Throws:
//     * @Author: Brady
//     * @Date: 5/31/2019 12:44 AM
//     */
    @ApiOperation(value = "删除员工")
    @ResponseBody
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    public RespBean deleteUserById(@RequestBody UserDeleteVo userDeleteVo, HttpServletRequest request) {
        int status = userService.deleteUserByUserId(userDeleteVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败!");
    }
    @ApiOperation(value = "修改员工密码")
    @ResponseBody
    @RequestMapping(value = "/updateUserPw", method = RequestMethod.POST)
    public RespBean updateUserPassword(@Valid @RequestBody UpdatePasswordVo updatePasswordVo, HttpServletRequest request) {
        int status = userService.updateUserPassword(updatePasswordVo);
        if (status != 0) {
            if (status == -1) {
                return RespBean.error("无权操作！");
            }
            return RespBean.okMessage("修改成功！");
        }
        return RespBean.error("修改失败!");
    }
    @ApiOperation(value = "角色列表")
    @ResponseBody
    @RequestMapping(value = "/listRole", method = RequestMethod.GET)
    public RespBean listRole() {
        List<Role> listRole = roleMapper.getListRole();
        if (listRole != null) {

            return RespBean.ok(listRole);
        }
        return RespBean.error("查询失败!");
    }

    @ApiOperation(value = "用户状态列表")
    @ResponseBody
    @RequestMapping(value = "/userState", method = RequestMethod.GET)
    public RespBean listUserState() {
        List<UserStateRespVo> listUserState = roleMapper.listUserState();
        if (listUserState != null) {

            return RespBean.ok(listUserState);
        }
        return RespBean.error("查询失败!");
    }



//    @Override
//    public MultipartFile updUserProfile(MultipartFile newProfile) {
//    String OSName = System.getProperty("os.name");
//    String profilesPath = OSName.toLowerCase().startsWith("win") ? SystemConstant.WINDOWS_PROFILES_PATH
//            : SystemConstant.LINUX_PROFILES_PATH;
//
//        if (!newProfile.isEmpty()) {
//        // 当前用户
//        SecurityProperties.User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
//        String profilePathAndNameDB = userDao.selectUserById(currentUser.getUserId()).getProfilePath();
//        // 默认以原来的头像名称为新头像的名称，这样可以直接替换掉文件夹中对应的旧头像
//        String newProfileName = profilePathAndNameDB;
//        // 若头像名称不存在
//        if (profilePathAndNameDB == null || "".equals(profilePathAndNameDB)) {
//            newProfileName = profilesPath+ System.currentTimeMillis()+ newProfile.getOriginalFilename();
//            // 路径存库
//            currentUser.setProfilePath(newProfileName);
//            userDao.updateUserProfilePath(currentUser);
//        }
//        // 磁盘保存
//        BufferedOutputStream out = null;
//        try {
//            File folder = new File(profilesPath);
//            if (!folder.exists())
//                folder.mkdirs();
//            out = new BufferedOutputStream(new FileOutputStream(newProfileName));
//            // 写入新文件
//            out.write(newProfile.getBytes());
//            out.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new SystemResult(HttpStatus.OK.value(), "设置头像失败", Boolean.FALSE);
//        } finally {
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return new SystemResult(HttpStatus.OK.value(), "设置头像成功", Boolean.TRUE);
//    } else {
//        return new SystemResult(HttpStatus.OK.value(), "设置头像失败", Boolean.FALSE);
//    }
//
//    }


}

