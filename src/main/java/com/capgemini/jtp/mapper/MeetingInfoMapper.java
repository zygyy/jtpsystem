package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.MeetingInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingInfoMapper {
    List<MeetingInfo> listMeeting();//返回所有内容
    MeetingInfo selectMeetingById(@Param("meetingId") int meetingId);
    Integer insertMeeting(@Param("MeetingInfo") MeetingInfo meetingInfo);//新增 0失败 1成功
    Integer deleteMeetingById(@Param("meetingId") int meetingId);//0失败 1成功
    Integer updateMeetingById(@Param("MeetingInfo") MeetingInfo meetingInfo);//更新 0失败 1成功 不更新作者

}
