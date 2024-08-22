package com.axy.controller;

import com.axy.pojo.Announcements;
import com.axy.service.AnnouncementsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
