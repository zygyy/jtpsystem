package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Classname : MenuMapper
 * @author: Jason Jin
 * @date: 2019/5/19 11:45 PM
 */
@Repository
public interface MenuMapper {

    List<Menu> getAllMenu();

    List<Menu> getMenusByUserId(int userId);

//    List<Menu> menuTree();
//
//    List<Long> getMenusByRid(Long rid);
//    List<Integer> getDisplayOrderBynodeId(int nodeId);
//
//    void setOneDisplayOrder(@Param("menu") Menu menu);
//
//    Menu selectMenuByParentNodeIdAndDisplayOrder(@Param("menu") Menu menu);
}
