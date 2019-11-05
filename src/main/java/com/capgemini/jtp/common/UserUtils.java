package com.capgemini.jtp.common;



import com.capgemini.jtp.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 18:49 2019/8/24
 */
public class UserUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
