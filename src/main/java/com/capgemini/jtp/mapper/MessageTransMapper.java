package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.Message;
import com.capgemini.jtp.entity.MessageTrans;
import com.capgemini.jtp.entity.User;
import com.capgemini.jtp.vo.request.MessageIfRead;
import com.capgemini.jtp.vo.request.MessageReadVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * create by: MmmLll_Shen
 * description:
 * create time: 9:53 2019/9/25
 */
@Service
public interface MessageTransMapper {

    Integer insertMessageTranses(@Param("messageTranses") List<MessageTrans> messageTranses);

    List<User> getRecipientsByMessageId(@Param("messageId") int messageId);

    List<Message> getMessagesByUserId(@Param("userId") int userId);

    List<Message> getMessagesByRecipientId(@Param("recipientId") int recipientId);

    List<Message> getMessagesByRecipientIdAndNotReadNotDeleted(@Param("recipientId") int recipientId);

    List<Message> getMessagesByRecipientIdAndNotReadDeleted(@Param("recipientId") int recipientId);

    List<Message> getMessagesByFromUserId(@Param("fromUserId") int fromUserId);

    List<Message> getMessagesByFromUserIdAndNotPublish(@Param("fromUserId") int fromUserId);

    List<Message> getDeletedMessages(@Param("fromUserId") int fromUserId);

    Integer readMessages(@Param("messageReadVo") MessageReadVo messageReadVo);

    Integer deleteMessageTranses(@Param("messageTransIds") List<Integer> messageTransIds);

    Integer deleteMessageTransesByMessageId(@Param("messageIds") List<Integer> messageIds);

    Integer deleteMessagesToCollection(@Param("messageIds") List<Integer> messageIds);

    String ifReanByUserIdAndMessageId(@Param("messageIfRead")MessageIfRead messageIfRead);

}
