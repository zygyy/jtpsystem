package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Message;
import com.capgemini.jtp.vo.request.MessageEditVo;
import com.capgemini.jtp.vo.request.MessageSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-26 11:04
 */
@Service
public interface MessageMapper {

     List<Message> listMessages(@Param("messageSearchVo") MessageSearchVo messageSearchVo);

     Integer countMessages(@Param("messageSearchVo") MessageSearchVo messageSearchVo);

     Message getMessage(@Param("messageId") int messageId);

     Integer insertMessage(@Param("messageEditVo") MessageEditVo messageEditVo);

     Integer updateMessage(@Param("messageEditVo") MessageEditVo messageEditVo);

     Integer publishMessage(@Param("messageId") int messageId);

     Integer deleteMessages(@Param("messageIds") List<Integer> messageIds);
}
