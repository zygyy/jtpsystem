package com.capgemini.jtp.service.impl;

import com.capgemini.jtp.mapper.FileManageMapper;
import com.capgemini.jtp.service.FileManageService;
import com.capgemini.jtp.utils.FileMenuUtils;
import com.capgemini.jtp.vo.response.FileTestRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
/**
 *
 */

public class FileManageServiceImpl implements FileManageService {
    @Autowired
    FileMenuUtils fileMenuUtils;
    @Autowired
    FileManageMapper fileManageMapper;

    /**
     * 三级菜单 最后是Map《String，list》
     * @return
     */
//    @Override
//    public Map<String ,Map<String ,List<String >>> threeMenu(){
//        List<String > list=fileManageMapper.getRootName();
//        return fileMenuUtils.threeMenu(list);
//    }
    /**
     * n级菜单 最后是Map《String，null》
     * @return
     */
    @Override
    public Map<FileTestRespVo, Object> nMenu(int n){
        fileMenuUtils.setTimes(n);
        List<FileTestRespVo > list=fileManageMapper.getRootName();
        return fileMenuUtils.nToMap(fileMenuUtils.listToMapNull(list));
    }



}




