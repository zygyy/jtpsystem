package com.capgemini.jtp.service.impl;


import com.capgemini.jtp.common.UserUtils;
import com.capgemini.jtp.entity.Depart;
import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.entity.User;
import com.capgemini.jtp.mapper.DepartMapper;
import com.capgemini.jtp.mapper.UserMapper;
import com.capgemini.jtp.service.UserService;
import com.capgemini.jtp.utils.ConvertUtils;
import com.capgemini.jtp.vo.request.UpdatePasswordVo;
import com.capgemini.jtp.vo.request.UserDeleteVo;
import com.capgemini.jtp.vo.request.UserEditVo;
import com.capgemini.jtp.vo.request.UserMassageVo;
import com.capgemini.jtp.vo.response.UserListVo;
import com.capgemini.jtp.vo.response.UserMassageRespVo;
import com.capgemini.jtp.vo.response.UserResponseVo;
import com.capgemini.jtp.vo.response.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: TODO
 * @Classname : HrService
 * @author: Jason Jin
 * @date: 2019/5/19 11:44 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartMapper departMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        System.out.println("用户名：" + s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return user;
    }

    @Override
    public List<UserListVo> listUserVo() {
        List<User> userList = userMapper.listUserVo();
        List<UserListVo> userListVos = new ArrayList<UserListVo>();
        for (User user : userList) {
            userListVos.add(ConvertUtils.convertUserEntityToListVo(user));
        }
        return userListVos;
    }
    @Override
    public UserMassageRespVo userMassage(UserMassageVo userMassageVo)
    {
        return userMapper.getMassageById(userMassageVo.getUserId());
    };
    @Override
    public List<UserMassageRespVo> userlistMassage(){
        return userMapper.getMassage();
    };


    @Override
    public int updateUser(UserEditVo userEditVo) {
        User user = new User();
        user.setUserId(UserUtils.getCurrentUser().getUserId());
        user.setChineseName(userEditVo.getChineseName());
        user.setPassword(userEditVo.getPassword());
        user.setChineseName(userEditVo.getChineseName());
        user.setDepartId(userEditVo.getDepartId());
        user.setGender(userEditVo.getGender());
        user.setRoleId(userEditVo.getRoleId());
        user.setUserStateId(userEditVo.getUserStateId());
        if(userEditVo.getPassword() != null && !("".equals(userEditVo.getPassword()))){
            System.out.println("密码不为空，执行密码加密");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(userEditVo.getPassword());
            user.setPassword(encode);
        }
        user.setUsername(userEditVo.getUsername());
        return userMapper.updateUser(user);
    }

    /**
     * 更改用户密码
     * @param updatePasswordVo
     * @return
     */
    @Override
    public int updateUserPassword(UpdatePasswordVo updatePasswordVo){
        User user = new User();
        user.setUserId(updatePasswordVo.getUserId());
        user.setChineseName(null);
        user.setPassword(null);
        user.setChineseName(null);
        user.setDepartId(-1);
        user.setGender(null);
        user.setRoleId(-1);
        user.setUserStateId(-1);
        if(updatePasswordVo.getPassword() != null && !("".equals(updatePasswordVo.getPassword()))){
            System.out.println("密码不为空，执行密码加密");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(updatePasswordVo.getPassword());
            user.setPassword(encode);
        }
        user.setUsername(null);
        return userMapper.updateUser(user);
    }


    @Override
    public Integer addUser(UserEditVo userEditVo){
        User user = ConvertUtils.convertUserVoToEntity(userEditVo);
        if(userEditVo.getPassword() != null && !("".equals(userEditVo.getPassword()))){
            System.out.println("密码不为空，执行密码加密");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(userEditVo.getPassword());
            user.setPassword(encode);
        }
        return userMapper.addUser(user);
    }
    @Override
    public Integer deleteUserByUserId(UserDeleteVo userDeleteVo){
        User user=new User();
        user.setUserId(userDeleteVo.getUserId());
        return userMapper.deleteUserByUserId(user);
    }

//    @Override
//    public UserDetails loadUserMessage(UserResponseVo userResponseVo){
//        return userMapper.loadUserMessage(userResponseVo);
//    }

    @Override
    public List<Integer> getAllUserIds() {
        return userMapper.getAllUserIds();
    }

    /**
     * 将User装换为UserVo
     */
    @Override
    public UserVo convertToVo(User user) {
        UserVo userVo = new UserVo();
        Depart department = departMapper.getDepartment(user.getDepartId());
//        暂时先不用
//        List<Role> roles = userRoleMapper.getRolesByUserId(user.getUserId());

        userVo.setUserId(user.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setChineseName(user.getChineseName());
        userVo.setGender(user.getGender());
//        头像暂时没用
//        userVo.setAvatar(user.getAvatar());
        userVo.setDepartId(user.getDepartId());
//        userVo.setDepartmentName(department == null ? null : department.getDepartName());
//        userVo.setRoles(roles);
        userVo.setUserStateId(user.getUserStateId());

        return userVo;
    }

}