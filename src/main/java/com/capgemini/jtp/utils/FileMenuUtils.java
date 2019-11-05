package com.capgemini.jtp.utils;


import com.capgemini.jtp.vo.response.FileTestRespVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
@Data
public class FileMenuUtils {
    @Autowired
    com.capgemini.jtp.mapper.FileManageMapper fileManageMapper;
//    static final int N=1;//N代表n级菜单
//    static int times=N-1;
    static int times;
    int t;

    /**
     * n 代表n级菜单
     * @param n
     */
    public void setTimes(int n){
        times=n-1;
    }

    /**
     * 读取同级的所有下一级列表
     * @param fileId
     * @return
     */
//    public  Map<String, List<String>> sameNext(String  fileId){
//        //点击fileId的操作
//        Map<String,List<String>> oneMap=new HashMap<>();
//        List<String> two;
//        List<String> list1;
//
//        String  name = fileManageMapper.getParent(fileId);
//        list1 = fileManageMapper.getChildren(name);
//        Iterator<String > iterator = list1.iterator();
//        while (iterator.hasNext())
//        {
//            String s=iterator.next();
//            two = fileManageMapper.getChildren(s);
//            oneMap.put(s,two);
//        }
//
//        return oneMap;
//    }

    /**
     * 读取下一级的所有列表
     * @param fileId
     * @return
     */
//    public  Map<String,List<String>> next(String fileId){
//        //点击fileId的操作
//        Map<String,List<String>> oneMap=new HashMap<>();
//        List<String> two;
//
//        two =fileManageMapper.getChildren(fileId);
//        oneMap.put(fileId,two);
//
//        return oneMap;
//    }

//    /**
//     * list 转为 map<String,list>
//     * @param list
//     * @return
//     */
//    public  Map<String,List<String >> listToMapList(List<String> list){
//        Map<String,List<String>> twoMap=new HashMap<>();
//        List<String> two;
//        //初始化
//
////        for(String l : list){
////            two =fileManageMapper.getChildren(l);
////            twoMap.put(l,two);
////        }
//        Iterator<String > iterator =list.iterator();
//        while (iterator.hasNext())
//        {
//            String l =iterator.next();
//            two=fileManageMapper.getChildren(l);
//            twoMap.put(l,two);
//        }
//        return twoMap;
//    }

    /**
     * list转为map<String,null>
     * @param list
     * @return
     */
    public  Map<FileTestRespVo ,Object> listToMapNull(List<FileTestRespVo> list){
        Map<FileTestRespVo,Object> twoMap=new HashMap<>();
        List<String> two;
        //初始化

        Iterator<FileTestRespVo > iterator =list.iterator();
        while (iterator.hasNext())
        {
            FileTestRespVo l =iterator.next();
            //two=null;
            twoMap.put(l,null);
        }
        return twoMap;
    }

    /**
     * n级菜单 最后是map
     * @param map
     * @return
     */
    public  Map<FileTestRespVo,Object> nToMap(Map<FileTestRespVo, Object> map){

        times-=1;
        Map<FileTestRespVo,List<FileTestRespVo>> twoMap=new HashMap<>();

        List<String> two;
        //初始化
        Set<? extends Map.Entry<FileTestRespVo, ?>> set = map.entrySet();
        for (Map.Entry<FileTestRespVo ,?> e: set) {
            FileTestRespVo s=e.getKey();
//            Map<String,Object>map1;
            Object map1;
            if(fileManageMapper.getChildren(s.getFileId()).isEmpty()||fileManageMapper.getChildren(s.getFileId())==null||times==0)
            {
                map1=listToMapNull(fileManageMapper.getChildren(s.getFileId()));
            }else if(times>0) {
                map1=nToMap(listToMapNull(fileManageMapper.getChildren(s.getFileId())));
            }else {
                 map1 =null;
            }
            map.put(s,map1);

        }
        return map;
    }
//    /**
//     * list转为map<String,null>
//     * @param list
//     * @return
//     */
//    public  Map<String ,Object> listToMapNull(List<String> list){
//        Map<String,Object> twoMap=new HashMap<>();
//        List<String> two;
//        //初始化
//
//        Iterator<String > iterator =list.iterator();
//        while (iterator.hasNext())
//        {
//            String l =iterator.next();
//            //two=null;
//            twoMap.put(l,null);
//        }
//        return twoMap;
//    }
//
//    /**
//     * n级菜单 最后是map
//     * @param map
//     * @return
//     */
//    public  Map<String,Object> nToMap(Map<String, Object> map){
//
//        times-=1;
//        Map<String,List<String>> twoMap=new HashMap<>();
//
//        List<String> two;
//        //初始化
//        Set<? extends Map.Entry<String, ?>> set = map.entrySet();
//        for (Map.Entry<String ,?> e: set) {
//            String s=e.getKey();
////            Map<String,Object>map1;
//            Object map1;
//            if(fileManageMapper.getChildren(s).isEmpty()||fileManageMapper.getChildren(s)==null||times==0)
//            {
//                map1=listToMapNull(fileManageMapper.getChildren(s));
//            }else if(times>0) {
//                map1=nToMap(listToMapNull(fileManageMapper.getChildren(s)));
//            }else {
//                map1 =null;
//            }
//            map.put(s,map1);
//
//        }
//        return map;
//    }

//    /**
//     * 三级菜单 最后是list
//     * @param list
//     * @return
//     */
//    public  Map<String ,Map<String ,List<String >>> threeMenu(List<String> list){
//        Map<String ,Map<String ,List<String >>> threeMap=new HashMap<>();
//        Map<String ,List<String >> twoMap;
//
//        twoMap=listToMapList(list);
//        Set<Map.Entry<String ,List<String >>> set = twoMap.entrySet();
//        for (Map.Entry<String ,List<String >> e: set) {
//            String s=e.getKey();
//            List<String > l =e.getValue();
//            threeMap.put(s,listToMapList(l));
//
//        }
//
//        return threeMap;
//    }
}
