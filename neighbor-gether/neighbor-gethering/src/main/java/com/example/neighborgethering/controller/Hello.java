package com.example.neighborgethering.controller;

import generator.pojo.Announcements;
import generator.service.AnnouncementsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Hello {

    @GetMapping
    public String hello(){
        return "hello yusang";
    }
    @Autowired
    private AnnouncementsService announcementsService;
    @GetMapping("/list")
    public List<Announcements> getAll(){
        return announcementsService.getAll();
    }
}
