package com.axy.controller;

import com.axy.pojo.Propertyinfo;
import com.axy.pojo.Users;
import com.axy.service.PropertyinfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
    // 修改信息
    @PostMapping("/update")
    public boolean update(@RequestBody Propertyinfo propertyinfo) {
        return propertyinfoService.updateById(propertyinfo);
    }

}
