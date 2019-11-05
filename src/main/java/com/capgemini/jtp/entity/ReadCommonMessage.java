package com.capgemini.jtp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ReadCommonMessage {
    /**
     * 序号id
     */
    private int readId;
    /**
     * 消息id
     */
    private int messageId;
    /**
     * 消息读取者；
     */
    private String userId;

}
