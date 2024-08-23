package com.axy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.pojo.Announcements;
import com.axy.service.AnnouncementsService;

import jakarta.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/announcements")

public class AnnouncementsController {
    @Resource
    private AnnouncementsService announcementsService;
    @GetMapping("/list")
    //展示公告
    public List<Announcements> list() {
        return announcementsService.list();
    }

    @PostMapping("/add")
    //添加公告
    public boolean add(@RequestBody Announcements announcements) {
        return announcementsService.save(announcements);
    }


    @DeleteMapping("/delete")
    //删除公告
    public boolean delete(@RequestParam("announcementid") int announcementid) {
        return announcementsService.removeById(announcementid);
    }
}
