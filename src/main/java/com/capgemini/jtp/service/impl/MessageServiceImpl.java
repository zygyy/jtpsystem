package com.capgemini.jtp.service.impl;


import com.capgemini.jtp.common.IdWorker;
import com.capgemini.jtp.common.UserUtils;
import com.capgemini.jtp.entity.*;
import com.capgemini.jtp.mapper.MessageMapper;
import com.capgemini.jtp.mapper.MessageTransMapper;
import com.capgemini.jtp.mapper.MessageTypeMapper;
import com.capgemini.jtp.mapper.UserMapper;
import com.capgemini.jtp.service.MessageService;
import com.capgemini.jtp.service.UserService;
import com.capgemini.jtp.utils.ConverLog;
import com.capgemini.jtp.utils.TimeFrame;
import com.capgemini.jtp.vo.base.RespVos;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.LoginLogResp;
import com.capgemini.jtp.vo.response.MessageVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hasaker
 * @since 2019/9/5 16:11
 */
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageMapper messageMapper;
    private final MessageTransMapper messageTransMapper;
    private final MessageTypeMapper messageTypeMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    public MessageServiceImpl(MessageMapper messageMapper,
                              MessageTransMapper messageTransMapper,
                              MessageTypeMapper messageTypeMapper,
                              UserMapper userMapper,
                              UserService userService) {
        this.messageMapper = messageMapper;
        this.messageTransMapper = messageTransMapper;
        this.messageTypeMapper = messageTypeMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public RespVos<MessageVo> listMessages(MessageSearchVo messageSearchVo) {
        Integer choose = messageSearchVo.getLimit();
        if (choose != null) {
            Date now = new Date();
            if (choose == 1) {
                messageSearchVo.setStartTime(TimeFrame.startOfDay(now));
                messageSearchVo.setEndTime(TimeFrame.endOfDay(now));
                System.out.println("sssssssssssssssssssssstime" + TimeFrame.endOfDay(now));
            } else if (choose == 2) {
                messageSearchVo.setStartTime(TimeFrame.firstDateOfWeek(now));
                messageSearchVo.setEndTime(TimeFrame.lastDateOfWeek(now));
            } else if (choose == 3) {
                messageSearchVo.setStartTime(TimeFrame.firstDateOfWonth(now));
                messageSearchVo.setEndTime(TimeFrame.lastDateOfMonth(now));
            }
        }

        List<Message> messages = messageMapper.listMessages(messageSearchVo);

        if (Objects.nonNull(messages) && messages.size() > 0) {
            RespVos<MessageVo> respVos = new RespVos<>();
            respVos.setSize(messageMapper.countMessages(messageSearchVo));
            respVos.setVos(messages.stream().map(this::convertToVo).collect(Collectors.toList()));

            return respVos;
        }

        return null;
    }

    @Override
    public RespVos<MessageVo> getMessage(int messageId) {
        Message message = messageMapper.getMessage(messageId);

        if (Objects.nonNull(message)) {
            return new RespVos<MessageVo>(){{
                setSize(1);
                setVos(new ArrayList<MessageVo>(){{
                    add(convertToVo(message));
                }});
            }};
        }

        return null;
    }

    @Override
    public RespVos<MessageVo> getMessagesByUserId(int userId) {
        List<Message> messages = messageTransMapper.getMessagesByUserId(userId);
        List<MessageVo> messageVos = new ArrayList<>();
        RespVos<MessageVo> respVos = new RespVos<>();

        for (Message message : messages) {
            messageVos.add(convertToVo(message));
        }

        respVos.setSize(messageVos.size());
        respVos.setVos(messageVos);

        return respVos;
    }

    @Override
    public RespVos<MessageVo> getMessagesByRecipientId(int recipientId) {
        List<Message> messages = messageTransMapper.getMessagesByRecipientId(recipientId);
        List<MessageVo> messageVos = new ArrayList<>();
        RespVos<MessageVo> respVos = new RespVos<>();

        for (Message message : messages) {
            messageVos.add(convertToVo(message));
        }

        //获取未读消息长度
        List<Message> notReadMessages = messageTransMapper.getMessagesByRecipientIdAndNotReadNotDeleted(recipientId);

        respVos.setSize(messageVos.size());
        respVos.setNotReadSize(notReadMessages.size());
        respVos.setVos(messageVos);

        return respVos;
    }

    @Override
    public RespVos<MessageVo> getMessagesByFromUserId(int fromUserId) {
        List<Message> messages = messageTransMapper.getMessagesByFromUserId(fromUserId);
        List<MessageVo> messageVos = new ArrayList<>();
        RespVos<MessageVo> respVos = new RespVos<>();

        for (Message message : messages) {
            messageVos.add(convertToVo(message));
        }

        respVos.setSize(messageVos.size());
        respVos.setVos(messageVos);

        return respVos;
    }

    @Override
    public RespVos<MessageVo> getMessagesByFromUserIdAndNotPublish(int fromUserId) {
        List<Message> messages = messageTransMapper.getMessagesByFromUserIdAndNotPublish(fromUserId);
        List<MessageVo> messageVos = new ArrayList<>();
        RespVos<MessageVo> respVos = new RespVos<>();

        for (Message message : messages) {
            messageVos.add(convertToVo(message));
        }

        respVos.setSize(messageVos.size());
        respVos.setVos(messageVos);

        return respVos;
    }

    @Override
    public RespVos<MessageVo> getDeletedMessages(int fromUserId) {
        List<Message> messages = messageTransMapper.getDeletedMessages(fromUserId);
        List<MessageVo> messageVos = new ArrayList<>();
        RespVos<MessageVo> respVos = new RespVos<>();

        for (Message message : messages) {
            messageVos.add(convertToVo(message));
        }

        //获取未读消息长度
        List<Message> notReadMessages = messageTransMapper.getMessagesByRecipientIdAndNotReadDeleted(fromUserId);

        respVos.setSize(messageVos.size());
        respVos.setNotReadSize(notReadMessages.size());
        respVos.setVos(messageVos);

        return respVos;
    }

    /**
     * 添加消息
     * 如果recipients为null 则表示发送给所有人
     */
    @Override
    public Integer addMessage(MessageEditVo messageEditVo) {
        Long messageId2 = IdWorker.get().nextId();
        List<MessageTrans> messageTranses = new ArrayList<>();
        int messageId = messageId2.intValue();
        messageEditVo.setMessageId(messageId);

        if (messageEditVo.getRecipientIds() != null && messageEditVo.getRecipientIds().size() != 0) {
            for (int recipientId : messageEditVo.getRecipientIds()) {
                messageTranses.add(new MessageTrans(){{
                    Long nextId2 = IdWorker.get().nextId();
                    int nextId = nextId2.intValue();
                    setId(nextId);
                    setMessageId(messageId);
                    setToUserId(recipientId);
                }});
            }
        } else {
            for (int recipientId : userService.getAllUserIds()) {
                messageTranses.add(new MessageTrans(){{
                    Long nextId2 = IdWorker.get().nextId();
                    int nextId = nextId2.intValue();
                    setId(nextId);
                    setMessageId(messageId);
                    setToUserId(recipientId);
                }});
            }
        }
        messageEditVo.setCreateTime(new Date());
        Integer res1 = messageMapper.insertMessage(messageEditVo);
        Integer res2 = messageTransMapper.insertMessageTranses(messageTranses);

        return res1 + res2;
    }

    @Override
    public Integer updateMessage(MessageEditVo messageEditVo) {
//        Long messageId2 = IdWorker.get().nextId();
        List<MessageTrans> messageTranses = new ArrayList<>();
//        int messageId = messageId2.intValue();
//        messageEditVo.setMessageId(messageId);

        if (messageEditVo.getRecipientIds() != null && messageEditVo.getRecipientIds().size() != 0) {
            for (int recipientId : messageEditVo.getRecipientIds()) {
                messageTranses.add(new MessageTrans(){{
                    Long nextId2 = IdWorker.get().nextId();
                    int nextId = nextId2.intValue();
                    setId(nextId);
                    setMessageId(messageEditVo.getMessageId());
                    setToUserId(recipientId);
                }});
            }
        } else {
            for (int recipientId : userService.getAllUserIds()) {
                messageTranses.add(new MessageTrans(){{
                    Long nextId2 = IdWorker.get().nextId();
                    int nextId = nextId2.intValue();
                    setId(nextId);
                    setMessageId(messageEditVo.getMessageId());
                    setToUserId(recipientId);
                }});
            }
        }
//        messageEditVo.setCreateTime(new Date());
        Integer res1 = messageMapper.updateMessage(messageEditVo);
        List<Integer> messageIdList = new ArrayList<>();
        messageIdList.add(messageEditVo.getMessageId());
        Integer res = messageTransMapper.deleteMessageTransesByMessageId(messageIdList);
        Integer res2 = messageTransMapper.insertMessageTranses(messageTranses);
        return res1 + res2;

    }

    @Override
    public Integer deleteMessages(List<Integer> messageIds) {
        messageTransMapper.deleteMessageTransesByMessageId(messageIds);

        return messageMapper.deleteMessages(messageIds);
    }

    @Override
    public Integer deleteMessagesToCollection(List<Integer> messageIds) {
        return messageTransMapper.deleteMessagesToCollection(messageIds);
    }

    @Override
    public Integer publishMessage(int messageId) {
        return messageMapper.publishMessage(messageId);
    }

    @Override
    public Integer readMessages(MessageReadVo messageReadVo) {
        messageReadVo.setCurrentUserId(UserUtils.getCurrentUser().getUserId());

        return messageTransMapper.readMessages(messageReadVo);
    }

    @Override
    public Integer deleteMessageTranses(DeleteVo deleteVo) {
        return messageTransMapper.deleteMessageTranses(deleteVo.getIds());
    }

    @Override
    public Integer deleteMessageTransesByMessageId(List<Integer> messageIds) {
        return messageTransMapper.deleteMessageTransesByMessageId(messageIds);
    }

    @Override
    public MessageVo convertToVo(Message message) {
        MessageVo messageVo = new MessageVo();

        User createUser = userMapper.getUser(message.getFromUserId());
        MessageType messageType = messageTypeMapper.getMessageType(message.getType());

        messageVo.setMessageId(message.getMessageId());
        messageVo.setMessageTypeId(message.getType());
        messageVo.setMessageTypeName(messageType == null ? null : messageType.getMessageTypeName());
        messageVo.setMessageTitle(message.getTitle());
        messageVo.setMessageContent(message.getContent());
        messageVo.setBeginTime(message.getBeginTime());
        messageVo.setEndTime(message.getEndTime());
        messageVo.setCreateUserId(message.getFromUserId());
        messageVo.setCreateUserName(createUser == null ? null : createUser.getChineseName());
        //
        messageVo.setCreateTime(message.getCreateTime());
        messageVo.setIsPublished(message.getIfPublish());
        messageVo.setPublishTime(message.getRecordTime());

        //判断消息是否被当前用户已读
        MessageIfRead messageIfRead = new MessageIfRead();
        messageIfRead.setUserId(UserUtils.getCurrentUser().getUserId());
        messageIfRead.setMessageId(message.getMessageId());
        messageVo.setIfReadByUser(messageTransMapper.ifReanByUserIdAndMessageId(messageIfRead));



        messageVo.setRecipients(messageTransMapper.getRecipientsByMessageId(message.getMessageId())
        .stream().map(userService::convertToVo).collect(Collectors.toList()));

        return messageVo;
    }


    @Override
    public List<User> listUser (MessageUserSearchVo messageUserSearchVo){
        List<User> userList = new ArrayList<>();
        userList = userMapper.selectUserListByMessageUser(messageUserSearchVo);
        return userList;
    }
}
