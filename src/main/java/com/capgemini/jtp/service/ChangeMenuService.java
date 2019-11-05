package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Menu;

import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 14:23 2019/9/19
 */
public interface ChangeMenuService {

//    public List<Menu> getAllMenu();
//
//    public List<Menu> getMenusByHrId();

//    public List<Menu> menuTree();
//
//    public List<Long> getMenusByRid(Long rid);

    public Boolean isCanUpMenu(Menu menu);

    public int upMenu(Menu menu);

    public Boolean isCanDoneMenu(Menu menu);

    public int doneMenu(Menu menu);
}
