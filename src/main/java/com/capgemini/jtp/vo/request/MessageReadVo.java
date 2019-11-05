package com.capgemini.jtp.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/6 21:03
 */
@Data
@ApiModel
public class MessageReadVo {

    int currentUserId;

    List<Integer> messageIds;
}
