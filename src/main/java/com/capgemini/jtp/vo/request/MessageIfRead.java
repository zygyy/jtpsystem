package com.capgemini.jtp.vo.request;

import com.capgemini.jtp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 10:09
 */
@Data
@ApiModel
public class MessageIfRead {

    private int messageId;

    private int userId;
}
