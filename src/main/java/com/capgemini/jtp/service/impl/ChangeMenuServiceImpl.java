package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.common.UserUtils;
import com.capgemini.jtp.entity.Menu;
import com.capgemini.jtp.mapper.ChangeMenuMapper;
import com.capgemini.jtp.mapper.MenuMapper;
import com.capgemini.jtp.service.ChangeMenuService;
import com.capgemini.jtp.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:菜单排序
 * create time: 9:27 2019/9/20
 */
@Slf4j
@Service
public class ChangeMenuServiceImpl implements ChangeMenuService {

    @Autowired
    ChangeMenuMapper changeMenuMapper;



    @Override
    public Boolean isCanUpMenu(Menu menu) {
        //首先判断该菜单是否在同级菜单的最上方
        List<Integer> departIdList = new ArrayList<>();
        departIdList = changeMenuMapper.getDisplayOrderListBynodeId(menu.getNodeId());
        int minDepartId = departIdList.get(0);
        System.out.println("--------------minDepartId=" + minDepartId);
        //相等，即已经是最上方
//        System.out.println("menu.getDisplayOrder()" + menu.getDisplayOrder());
        int displayOrder = changeMenuMapper.getDisplayOrderBynodeId(menu.getNodeId());
        if (displayOrder > minDepartId){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int upMenu(Menu menu) {
        int displayOrder = menu.getDisplayOrder();
        int newDisplayOrder = displayOrder - 1;
        System.out.println("1----------------" + menu.getParentNodeId());
        menu.setDisplayOrder(newDisplayOrder);
        System.out.println("2----------------" + menu.getParentNodeId());
        changeMenuMapper.setOneDisplayOrder(menu);
        System.out.println("3----------------" + menu.getParentNodeId());
        //根据信息查找对调的对象
        Menu twoMenu = changeMenuMapper.selectMenuByParentNodeIdAndDisplayOrder(menu);
        twoMenu.setDisplayOrder(displayOrder);
        changeMenuMapper.setOneDisplayOrder(twoMenu);
        return 1;
    }



    @Override
    public Boolean isCanDoneMenu(Menu menu) {
        //首先判断该菜单是否在同级菜单的最下方
        List<Integer> departIdList = new ArrayList<>();
        departIdList = changeMenuMapper.getDisplayOrderListBynodeId(menu.getNodeId());
        int maxDepartId = departIdList.get(departIdList.size() - 1);
        System.out.println("--------------minDepartId=" + maxDepartId);
        //相等，即已经是最下方
//        System.out.println("menu.getDisplayOrder()" + menu.getDisplayOrder());

        int displayOrder = changeMenuMapper.getDisplayOrderBynodeId(menu.getNodeId());
        if (displayOrder < maxDepartId){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int doneMenu(Menu menu) {
        int displayOrder = menu.getDisplayOrder();
        int newDisplayOrder = displayOrder + 1;
        System.out.println("1----------------" + menu.getParentNodeId());
        menu.setDisplayOrder(newDisplayOrder);
        System.out.println("2----------------" + menu.getParentNodeId());
        changeMenuMapper.setOneDisplayOrder(menu);
        System.out.println("3----------------" + menu.getParentNodeId());
        //根据信息查找对调的对象
        Menu twoMenu = changeMenuMapper.selectMenuByParentNodeIdAndDisplayOrder(menu);
        twoMenu.setDisplayOrder(displayOrder);
        changeMenuMapper.setOneDisplayOrder(twoMenu);
        return 1;
    }

//    @Override
//    public List<Menu> menuTree() {
//        return menuMapper.menuTree();
//    }
//
//    @Override
//    public List<Long> getMenusByRid(Long rid) {
//        return menuMapper.getMenusByRid(rid);
//    }
}
