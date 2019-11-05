package com.capgemini.jtp.service;



import com.capgemini.jtp.entity.MyNote;
import com.capgemini.jtp.vo.request.MyNoteAddVo;
import com.capgemini.jtp.vo.request.MyNoteVo;
import com.capgemini.jtp.vo.response.FileTestRespVo;

import java.util.List;
import java.util.Map;

public interface FileManageService {
    //Map<String , Map<String ,List<String >>> threeMenu();
    Map<FileTestRespVo, Object> nMenu(int n);
}
