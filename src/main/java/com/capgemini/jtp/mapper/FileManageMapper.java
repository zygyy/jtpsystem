package com.capgemini.jtp.mapper;


import com.capgemini.jtp.entity.MyNote;
import com.capgemini.jtp.vo.response.FileTestRespVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileManageMapper {
      List<FileTestRespVo > getChildren(@Param("parent")int   parent);
      FileTestRespVo  getParent(@Param("children")int   children);
      List<FileTestRespVo> getRootName();

}
