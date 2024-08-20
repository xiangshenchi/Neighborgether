package com.axy.controller;

import com.axy.common.QueryPageParam;
import com.axy.common.Result;
import com.axy.pojo.Announcements;
import com.axy.service.AnnouncementsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.apache.catalina.webresources.AbstractResourceSet;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/announcements")

public class AnnouncementsController {
    @Resource
    private AnnouncementsService announcementsService;
    @GetMapping("/list")
    //展示公告
    /*public List<Announcements> list() {
        return announcementsService.list();
    }
    @PostMapping("/save")
    public boolean save(@RequestBody User user) {//        return userService.list();
        return userService.save(user);
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user) {//        return userService.list();
        return userService.updateById(user);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {//        return userService.list();
        return userService.saveOrUpdate(user);
    }*/
    @PostMapping("/AnFind")
    public List<Announcements> listP(@RequestBody Announcements ano) {//        return userService.list();
        LambdaQueryWrapper<Announcements> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(Announcements::getTitle, ano.getTitle());
        return announcementsService.list(queryWrapper);
    }


    //分页:第三种方式--使用mybatis-plus分页插件
    @PostMapping("/listPage")
    public List<Announcements> listPage(@RequestBody QueryPageParam query) {//        return userService.list();
        HashMap param = query.getParam();
        String title = (String) param.get("title");
        System.out.println("title===" + (String) param.get("title"));

        Page<Announcements> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());


        LambdaQueryWrapper<Announcements> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(Announcements::getTitle,title);

        IPage result = announcementsService.page(page,queryWrapper);
        System.out.println("total===" + result.getTotal());

        return result.getRecords();
    }


    //分页:第四种方式--使用 编写分页mapper & 自定义SQL使用wrapper ==> 查询官网
    /*【非常简单实用的前后端分离项目-仓库管理系统（Springboot+Vue）】【精准空降到 27:23】
    https://www.bilibili.com/video/BV1Qe411V7TZ/?p=6&share_source=copy_web&vd_source=b17c24fe94c710154c6bd9d6f16e547c&t=1643*/
    @PostMapping("/listPageC")
    public List<Announcements> listPageC(@RequestBody QueryPageParam query) {//        return userService.list();
        HashMap param = query.getParam();
        String title = (String) param.get("title");
        System.out.println("title===" + (String) param.get("title"));
//        System.out.println("no===" + (String)param.get("no"));

        Page<Announcements> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        /*LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(User::getName,name);*/

        IPage result = announcementsService.pageC(page);//只考虑分页
        System.out.println("total===" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listBack")
    public Result listBack(@RequestBody QueryPageParam query) {//        return userService.list();
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        System.out.println("name===" + (String) param.get("name"));
//        System.out.println("no===" + (String)param.get("no"));

        Page<Announcements> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        /*LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(User::getName,name);*/

        IPage result = announcementsService.pageC(page);//只考虑分页
        System.out.println("total===" + result.getTotal());

        return Result.success(result.getTotal(),result.getRecords());
    }
}


