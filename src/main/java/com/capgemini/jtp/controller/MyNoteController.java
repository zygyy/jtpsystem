package com.capgemini.jtp.controller;


import com.capgemini.jtp.entity.MyNote;
import com.capgemini.jtp.service.MyNoteService;
import com.capgemini.jtp.vo.base.RespBean;
import com.capgemini.jtp.vo.request.MyNoteAddVo;
import com.capgemini.jtp.vo.request.MyNoteDeleteVo;
import com.capgemini.jtp.vo.request.MyNoteVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api("我的便签")
@RequestMapping("/mynote")
public class MyNoteController {
    @Autowired
    MyNoteService myNoteService;
    //返回所有内容
    @ApiOperation(value = "便签列表")
    @ResponseBody
    @RequestMapping(value = "/listnote", method = RequestMethod.POST)
    public RespBean listNote() {
        List<MyNote> list=myNoteService.listNote();
        if (list.size()>0)
        return RespBean.ok("便签查询成功",list);
        else
            return RespBean.error("便签查询失败");
    }
    @ApiOperation(value = "指定便签列表")
    @ResponseBody
    @GetMapping("/listnotebyName/{userName}")
    public RespBean listNoteByUserName(@PathVariable("userName")String  userName) {
        List<MyNote> list=myNoteService.listNoteByUserName(userName);
        if (list.size()>0)
            return RespBean.ok("便签查询成功",list);
        else
            return RespBean.error("便签查询失败");
    }
    @ApiOperation(value = "指定便签id的信息")
    @ResponseBody
    @GetMapping("/notebynoteid/{noteId}")
    public RespBean noteByNoteId(@PathVariable("noteId")int noteId  ) {
        List<MyNote> list=myNoteService.noteByNoteId(noteId);
        if (list.size()>0)
            return RespBean.ok("便签查询成功",list);
        else
            return RespBean.error("便签查询失败");
    }

    //
    @ApiOperation(value = "新增便签")
    @ResponseBody
    @RequestMapping(value = "/addnote", method = RequestMethod.POST)
    public RespBean addNote(@Valid @RequestBody MyNoteAddVo myNoteAddVo) {

        if (myNoteService.addNote(myNoteAddVo)!=0)
        return RespBean.okMessage("便签添加成功");
        else
            return RespBean.error("便签添加失败");
    }

    //只需要接受noteId
    @ApiOperation(value = "删除便签")
    @ResponseBody
    @RequestMapping(value = "/deletenote", method = RequestMethod.POST)
    public RespBean deleteNote(@Valid @RequestBody MyNoteDeleteVo myNoteDeleteVo) {
        if(myNoteService.deleteNote(myNoteDeleteVo.getNoteId())!=0)
        return RespBean.okMessage("便签删除成功");
        else
        return RespBean.error("便签删除失败");
    }

    //根据noteId修改 不修改作者
    @ApiOperation(value = "修改便签")
    @ResponseBody
    @RequestMapping(value = "/updatenote", method = RequestMethod.POST)
    public RespBean updateNote(@Valid @RequestBody MyNoteVo myNoteVo) {

        if(myNoteService.updateNote(myNoteVo)!=0)
            return RespBean.okMessage("便签修改成功");
        else
            return RespBean.error("便签修改失败");
    }
}

