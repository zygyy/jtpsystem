package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.FileInfo;
import com.capgemini.jtp.entity.Menu;

import java.util.List;
import java.util.Map;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 14:23 2019/9/19
 */
public interface MenuService {

    public List<Menu> getAllMenu();

    public List<Menu> getMenusByHrId();
//    public Map<String, List<FileInfo>> getMenuTree();
//    Map<String, List<FileInfo>> getMenuTree();
    public List<FileInfo> getTree();
//    public List<FileInfo> getTree();
//
//    public List<Long> getMenusByRid(Long rid);

//    public Boolean isCanUpMenu(Menu menu);
//
//    public int upMenu(Menu menu);
}
