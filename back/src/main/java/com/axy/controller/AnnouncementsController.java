package com.axy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.mapper.AnnouncementsMapper;
import com.axy.pojo.Announcements;
import com.axy.service.AnnouncementsService;

import jakarta.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/announcements")

public class AnnouncementsController {
    @Resource
    private AnnouncementsService announcementsService;
    private final AnnouncementsMapper announcementsMapper;

    public AnnouncementsController(AnnouncementsMapper announcementsMapper) {
        this.announcementsMapper = announcementsMapper;
    }

    @GetMapping("/list")
    //展示公告
    public List<Announcements> list() {
        return announcementsService.list();
    }

//    @PostMapping("/add")
//    //添加公告
//    public boolean add(@RequestBody Announcements announcements) {
//        return announcementsService.save(announcements);
//    }


    @DeleteMapping("/delete")
    //删除公告
    public boolean delete(@RequestParam("announcementid") int announcementid) {
        return announcementsService.removeById(announcementid);
    }
    //编辑公告
    @PostMapping("/edit")
    public Map<String, Object> adedit(@RequestBody Announcements announcements){
        Map<String, Object> response = new HashMap<>();
        int announcementid=announcements.getAnnouncementid();
        String title=announcements.getTitle();
        String content=announcements.getContent();
        Announcements an=announcementsMapper.getbyphonenumber(announcementid);
        an.setTitle(title);
        an.setContent(content);
        boolean isUpdated = announcementsService.updateById(an);
        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "公告信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "公告信息修改失败");
        }
        return response;
    }
    //添加公告
    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Announcements announcements) {
        Map<String, Object> response = new HashMap<>();
        String title = announcements.getTitle();
        String content = announcements.getContent();
        announcements.setTitle(title);
        announcements.setContent(content);
        boolean isSaved = announcementsService.save(announcements);
        if (isSaved) {
            response.put("status", "1");
            response.put("message", "公告信息添加成功");
        } else {
            response.put("status", "0");
            response.put("message", "公告信息添加失败");
        }
        return response;
    }

}
