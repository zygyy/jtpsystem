package com.capgemini.jtp.service;

//import com.capgemini.cn.deemo.data.domain.Message;
//import com.capgemini.cn.deemo.vo.base.RespVos;
//import com.capgemini.cn.deemo.vo.request.DeleteVo;
//import com.capgemini.cn.deemo.vo.request.MessageEditVo;
//import com.capgemini.cn.deemo.vo.request.MessageReadVo;
//import com.capgemini.cn.deemo.vo.request.MessageSearchVo;
//import com.capgemini.cn.deemo.vo.response.MessageVo;

import com.capgemini.jtp.entity.Message;
import com.capgemini.jtp.entity.User;
import com.capgemini.jtp.vo.base.RespVos;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.MessageVo;

import java.util.List;

/**
 * @author Bowen Du
 * @since 2019-08-22 17:53
 */

public interface MessageService {

    RespVos<MessageVo> listMessages(MessageSearchVo messageSearchVo);

    RespVos<MessageVo> getMessage(int messageId);

    public RespVos<MessageVo> getMessagesByUserId(int userId);

    RespVos<MessageVo> getMessagesByRecipientId(int recipientId);

    public RespVos<MessageVo> getMessagesByFromUserId(int fromUserId);

    public RespVos<MessageVo> getMessagesByFromUserIdAndNotPublish(int fromUserId);

    public RespVos<MessageVo> getDeletedMessages(int fromUserId);

    Integer addMessage(MessageEditVo messageEditVo);

    Integer updateMessage(MessageEditVo messageEditVo);

    Integer deleteMessages(List<Integer> messageIds);

    Integer deleteMessagesToCollection(List<Integer> messageIds);

    Integer publishMessage(int messageId);

    Integer readMessages(MessageReadVo messageReadVo);

    Integer deleteMessageTranses(DeleteVo deleteVo);

    Integer deleteMessageTransesByMessageId(List<Integer> messageIds);

    MessageVo convertToVo(Message message);

    List<User> listUser(MessageUserSearchVo messageUserSearchVo);
}
