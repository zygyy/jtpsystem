package com.capgemini.jtp.service;

//import com.capgemini.jtp.entity.Hr;
//import com.capgemini.jtp.entity.Message;
//import com.capgemini.jtp.vo.employee.request.HrEditVo;
//import com.capgemini.jtp.vo.employee.request.HrSearchVo;
//import com.capgemini.jtp.vo.employee.response.HrResponseVo;
import com.capgemini.jtp.entity.User;
import com.capgemini.jtp.vo.request.UpdatePasswordVo;
import com.capgemini.jtp.vo.request.UserDeleteVo;
import com.capgemini.jtp.vo.request.UserEditVo;
import com.capgemini.jtp.vo.request.UserMassageVo;
import com.capgemini.jtp.vo.response.UserListVo;
import com.capgemini.jtp.vo.response.UserMassageRespVo;
import com.capgemini.jtp.vo.response.UserResponseVo;
import com.capgemini.jtp.vo.response.UserVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Classname : HrService
 * @author: Jason Jin
 * @date: 2019/5/19 11:44 PM
 */
public interface UserService extends UserDetailsService {

//    int hrReg(HrEditVo hrEditVo);
//
//    List<Hr> getHrsByKeywords(String keywords);
//
    int updateUser(UserEditVo userEditVo);
    int updateUserPassword(UpdatePasswordVo updatePasswordVo);

    List<UserListVo> listUserVo();

//    UserDetails loadUserMessage(UserResponseVo userResponseVo);

    UserMassageRespVo userMassage(UserMassageVo userMassageVo);

    List<UserMassageRespVo> userlistMassage();


//    public MultipartFile updUserProfile(UserEditVo userEditVo);


    Integer addUser(UserEditVo userEditVo);

    Integer deleteUserByUserId(UserDeleteVo userDeleteVo);


    /**
     * create by: MmmLll_Shen
     * description:以下方法为消息新增
     * create time: 11:09 2019/9/25
     */
    List<Integer> getAllUserIds();

    UserVo convertToVo(User user);


}