package com.axy.controller;

import com.axy.pojo.Propertyinfo;
import com.axy.service.PropertyinfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/propertyinfo")
public class PropertyinfoController {
    @Resource
    private PropertyinfoService propertyinfoService;

   //展示所有房产信息
   @GetMapping("/list")
    public List<Propertyinfo> list() {
        return propertyinfoService.list();
    }


}
