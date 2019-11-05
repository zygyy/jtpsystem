package com.capgemini.jtp.controller;

//import com.capgemini.cn.deemo.annotation.ControllerLog;
//import com.capgemini.cn.deemo.service.MessageService;
//import com.capgemini.cn.deemo.utils.UserUtils;
//import com.capgemini.cn.deemo.vo.base.RespBean;
//import com.capgemini.cn.deemo.vo.base.RespVos;
//import com.capgemini.cn.deemo.vo.request.DeleteVo;
//import com.capgemini.cn.deemo.vo.request.MessageEditVo;
//import com.capgemini.cn.deemo.vo.request.MessageReadVo;
//import com.capgemini.cn.deemo.vo.request.MessageSearchVo;
//import com.capgemini.cn.deemo.vo.response.MessageVo;
import com.capgemini.jtp.common.UserUtils;
import com.capgemini.jtp.entity.User;
import com.capgemini.jtp.service.MessageService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.base.RespVos;
import com.capgemini.jtp.vo.request.*;
import com.capgemini.jtp.vo.response.MessageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/5 22:00
 */
@Api("消息系统")
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation("获取一条消息")
    @GetMapping("/{messageId}")
    public RespBean getMessage(@PathVariable int messageId) {
        RespVos<MessageVo> respVos = messageService.getMessage(messageId);

        if (respVos != null && respVos.getSize() == 1) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("未找到相关消息!");
    }

    @ApiOperation("获取信息列表")
    @PostMapping("/list")
    public RespBean listMessages(@RequestBody MessageSearchVo messageSearchVo) {
        RespVos<MessageVo> respVos = messageService.listMessages(messageSearchVo);

        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }

        return RespBean.error("查询失败!");
    }

    @ApiOperation("获取与当前用户相关的所有消息!")
    @GetMapping("/getMessagesByUserId")
    public RespBean getMessagesByUserId() {
        RespVos<MessageVo> respVos = messageService.getMessagesByUserId(
                UserUtils.getCurrentUser().getUserId()
        );
        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }
        return RespBean.ok("没有我的相关消息!");
    }

    @ApiOperation("获取发送给当前用户的所有消息!")
    @GetMapping("/")
    public RespBean getMessagesByRecipientId() {
        RespVos<MessageVo> respVos = messageService.getMessagesByRecipientId(
                UserUtils.getCurrentUser().getUserId()
        );
        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }
        return RespBean.ok("没有我的相关消息!");
    }

    @ApiOperation("获取当前用户已经发送的所有信息")
    @GetMapping("/getPublishedMessages")
    public RespBean getPublishedMessages() {
        RespVos<MessageVo> respVos = messageService.getMessagesByFromUserId(
                UserUtils.getCurrentUser().getUserId()
        );
        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }
        return RespBean.ok("我还没有发布过消息!");
    }

    @ApiOperation("获取当前用户草稿箱的所有信息")
    @GetMapping("/getNotPublishedMessages")
    public RespBean getNotPublishedMessages() {
        RespVos<MessageVo> respVos = messageService.getMessagesByFromUserIdAndNotPublish(
                UserUtils.getCurrentUser().getUserId()
        );
        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }
        return RespBean.ok("我的草稿箱没有消息!");
    }

    @ApiOperation("获取当前用户回收站的所有信息")
    @GetMapping("/getDeletedMessages")
    public RespBean getDeletedMessages() {
        RespVos<MessageVo> respVos = messageService.getDeletedMessages(
                UserUtils.getCurrentUser().getUserId()
        );
        if (respVos != null && respVos.getSize() > 0) {
            return RespBean.ok(respVos);
        }
        return RespBean.ok("我的回收站没有消息!");
    }


//    @ControllerLog(name = "添加信息为草稿")
    @ApiOperation("添加消息为草稿")
    @PostMapping("/add")
    public RespBean addMessage(@RequestBody MessageEditVo messageEditVo) {
        messageEditVo.setCreateUserId(UserUtils.getCurrentUser().getUserId());
        return messageService.addMessage(messageEditVo) > 0 ? RespBean.ok("添加成功!") : RespBean.error("添加失败!");
    }

//    @ControllerLog(name = "修改草稿")
    @ApiOperation("修改草稿")
    @PostMapping("/update")
    public RespBean updateMessage(@RequestBody MessageEditVo messageEditVo) {
        return messageService.updateMessage(messageEditVo) > 0 ? RespBean.ok("更新成功!") : RespBean.error("更新失败!");
    }

//    @ControllerLog(name = "删除消息")
    @ApiOperation("彻底删除消息")
    @PostMapping("/deleteMessages")
    public RespBean deleteMessages(@RequestBody DeleteVo deleteVo) {
        Integer res = messageService.deleteMessages(deleteVo.getIds());

        return res > 0 ? RespBean.ok("成功删除" + res + "条消息!") : RespBean.error("删除失败!");
    }

    @ApiOperation("删除消息到回收站")
    @PostMapping("/deleteMessagesToCollection")
    public RespBean deleteMessagesToCollection(@RequestBody DeleteVo deleteVo) {
        Integer res = messageService.deleteMessagesToCollection(deleteVo.getIds());

        return res > 0 ? RespBean.ok("成功删除" + res + "条消息!") : RespBean.error("删除失败!");
    }

//    @ControllerLog(name = "发送消息")
    @ApiOperation("发送消息")
    @GetMapping("/publish/{messageId}")
    public RespBean sendMessage(@PathVariable int messageId) {
        Integer res = messageService.publishMessage(messageId);

        return res > 0 ? RespBean.ok("成功将消息发送给" + res + "个用户!") : RespBean.error("发送失败!");
    }

//    @ControllerLog(name = "阅读消息")
    @ApiOperation("阅读消息/将消息标记为已读")
    @PostMapping("/read")
    public RespBean readMessages(@RequestBody MessageReadVo messageReadVo) {
        Integer res = messageService.readMessages(messageReadVo);

        return res > 0 ? RespBean.ok("成功阅读" + res + "条信息!") : RespBean.error("阅读失败!");
    }

//    @ControllerLog(name = "删除收件箱里的消息")
//    @ApiOperation("删除收件箱里的消息")
//    @PostMapping("/deleteMessageTranses")
//    RespBean deleteMessageTranses(@RequestBody DeleteVo deleteVo) {
//        Integer res = messageService.deleteMessageTranses(deleteVo);
//
//        return res > 0 ? RespBean.ok("成功删除" + res + "条信息!") : RespBean.error("删除失败!");
//    }

    @ApiOperation("根据搜索项查找对应的users")
    @PostMapping("/selectUsers")
    public RespBean selectUsers(@RequestBody MessageUserSearchVo messageUserSearchVo) {
        List<User> userList = new ArrayList<>();
        userList = messageService.listUser(messageUserSearchVo);
        if(userList != null){
            return RespBean.ok(userList);
        }else {
            return RespBean.error("无搜索项");
        }
    }





}
