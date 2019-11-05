package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.MessageType;
import com.capgemini.jtp.vo.request.MessageTypeEditVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:08
 */
@Service
public interface MessageTypeMapper {

    List<MessageType> listMessageTypes();

    MessageType getMessageType(@Param("messageTypeId") int messageTypeId);

    Integer insertMessageType(@Param("messageTypeEditVo") MessageTypeEditVo messageTypeEditVo);

    Integer updateMessageType(@Param("messageTypeEditVo") MessageTypeEditVo messageTypeEditVo);

    Integer deleteMessageTypes(@Param("messageTypeIds") List<Integer> messageTypeIds);
}
