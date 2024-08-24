package com.axy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.axy.pojo.Announcements;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Mapper
* @createDate 2024-08-18 08:50:22
* @Entity com.axy.pojo.Announcements
*/
@Mapper
public interface AnnouncementsMapper extends BaseMapper<Announcements> {


    Announcements getbyphonenumber(int announcementphonenumber);
}




