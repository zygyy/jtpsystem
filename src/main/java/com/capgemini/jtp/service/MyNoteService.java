package com.capgemini.jtp.service;



import com.capgemini.jtp.entity.MyNote;
import com.capgemini.jtp.vo.request.MyNoteAddVo;
import com.capgemini.jtp.vo.request.MyNoteVo;

import java.util.List;

public interface MyNoteService {
    public List<MyNote> listNote();
    public List<MyNote> listNoteByUserName(String  userName);
    public List<MyNote> noteByNoteId(int noteId);
    public Integer addNote(MyNoteAddVo myNoteAddVo);
    public Integer deleteNote(int noteId);
    public void findNote();
    public Integer updateNote(MyNoteVo myNoteVo);
}
