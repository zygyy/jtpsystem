package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.UserUtils;
import com.capgemini.jtp.entity.FileInfo;
import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.entity.Role;
import com.capgemini.jtp.mapper.FileInfoMapper;
import com.capgemini.jtp.mapper.MenuMapper;
import com.capgemini.jtp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Classname : MenuService
 * @author: Jason Jin
 * @date: 2019/5/19 11:43 PM
 */
@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    FileInfoMapper fileInfoMapper;

//    @Cacheable(key = "#root.methodName")
    @Override
    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    @Override
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByUserId(UserUtils.getCurrentUser().getUserId());
    }




    @Override
    public List<FileInfo> getTree() {
        List<FileInfo> tree = fileInfoMapper.getTree();//返回所有父级
        List<FileInfo> allTree = fileInfoMapper.getAllMenus();//返回所有数据
        List<FileInfo> treeList = new ArrayList<>();
        for (FileInfo list : tree) {

            list.setChildren(treeList(allTree,list.getFileId()));
            treeList.add(list);
            }

        return treeList;
    }
    private static List<FileInfo> treeList(List<FileInfo> list, Integer rootId) {
        List<FileInfo> newList = new ArrayList<>();
        for (FileInfo tree : list) {
            if (tree.getParentId() == rootId.intValue()) {//找她儿子
                List<FileInfo> tempList = treeList(list, tree.getFileId());
                tree.setChildren(tempList);
                newList.add(tree);
            }
        }
        return newList;
    }


}
