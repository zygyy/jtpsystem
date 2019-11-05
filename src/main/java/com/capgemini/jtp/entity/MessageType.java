package com.capgemini.jtp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageType {
    /**
     * 消息类型Id
     */
    private int messageTypeId;
    /**
     * 消息类型名称Id
     */
    private String messageTypeName;
    /**
     * 消息类型描述；
     */
    private String messageDesc;

}
