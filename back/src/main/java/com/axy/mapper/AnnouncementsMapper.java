package com.axy.mapper;

import com.axy.pojo.Announcements;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Mapper
* @createDate 2024-08-18 08:50:22
* @Entity com.axy.pojo.Announcements
*/
@Mapper
public interface AnnouncementsMapper extends BaseMapper<Announcements> {

    //展示公告
     List<Announcements> list();
    //增加公告
    boolean add(@RequestBody Announcements announcements);
    //删除公告
    boolean delete(@RequestParam("announcementid") int announcementid);
}




