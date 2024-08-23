package com.axy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.pojo.Demand;
import com.axy.service.DemandService;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/demand")

public class DemandController {
    @Resource
    private DemandService demandService;
    @GetMapping("/list")
    //展示投诉建议
    public List<Demand> list(){
        return demandService.list();
    }
    @PostMapping("/add")
    //增加投诉建议
    public boolean add(@RequestBody Demand demand){
        return demandService.save(demand);
    }
    @DeleteMapping("/delete")
    //删除投诉建议
    public boolean delete(@RequestParam("demandid") int demandid){
        return demandService.removeById(demandid);
        
    }
}
