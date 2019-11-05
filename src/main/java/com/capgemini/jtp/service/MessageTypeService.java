package com.capgemini.jtp.service;


import com.capgemini.jtp.vo.base.RespVos;
import com.capgemini.jtp.vo.request.DeleteVo;
import com.capgemini.jtp.vo.request.MessageTypeEditVo;
import com.capgemini.jtp.vo.response.MessageTypeVo;

/**
 * @author hasaker
 * @since 2019/9/5 15:59
 */
public interface MessageTypeService {

    RespVos<MessageTypeVo> listMessageTypes();

    Integer insertMessageType(MessageTypeEditVo messageTypeEditVo);

    Integer updateMessageType(MessageTypeEditVo messageTypeEditVo);

    Integer deleteMessageTypes(DeleteVo deleteVo);
}
