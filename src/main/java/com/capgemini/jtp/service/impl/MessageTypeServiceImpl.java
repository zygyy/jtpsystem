package com.capgemini.jtp.service.impl;


import com.capgemini.jtp.common.IdWorker;
import com.capgemini.jtp.entity.MessageType;
import com.capgemini.jtp.mapper.MessageTypeMapper;
import com.capgemini.jtp.service.MessageTypeService;
import com.capgemini.jtp.vo.base.RespVos;
import com.capgemini.jtp.vo.request.DeleteVo;
import com.capgemini.jtp.vo.request.MessageTypeEditVo;
import com.capgemini.jtp.vo.response.MessageTypeVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/5 21:18
 */
@Service
public class MessageTypeServiceImpl implements MessageTypeService {

    private final MessageTypeMapper messageTypeMapper;

    public MessageTypeServiceImpl(MessageTypeMapper messageTypeMapper) {
        this.messageTypeMapper = messageTypeMapper;
    }

    @Override
    public RespVos<MessageTypeVo> listMessageTypes() {
        List<MessageType> messageTypes = messageTypeMapper.listMessageTypes();

        if (Objects.nonNull(messageTypes) && messageTypes.size() > 0) {
            RespVos<MessageTypeVo> respVos = new RespVos<>();
            respVos.setSize(messageTypes.size());
            respVos.setVos(messageTypes.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public Integer insertMessageType(MessageTypeEditVo messageTypeEditVo) {
        Long nextId2 = IdWorker.get().nextId();
        int nextId = nextId2.intValue();
        messageTypeEditVo.setMessageTypeId(nextId);

        return messageTypeMapper.insertMessageType(messageTypeEditVo);
    }

    @Override
    public Integer updateMessageType(MessageTypeEditVo messageTypeEditVo) {
        return messageTypeMapper.updateMessageType(messageTypeEditVo);
    }

    @Override
    public Integer deleteMessageTypes(DeleteVo deleteVo) {
        return messageTypeMapper.deleteMessageTypes(deleteVo.getIds());
    }

    private MessageTypeVo convertToVo(MessageType messageType) {
        MessageTypeVo messageTypeVo = new MessageTypeVo();

        messageTypeVo.setMessageTypeId(messageType.getMessageTypeId());
        messageTypeVo.setMessageTypeName(messageType.getMessageTypeName());
        messageTypeVo.setMessageTypeDesc(messageType.getMessageDesc());

        return messageTypeVo;
    }
}
