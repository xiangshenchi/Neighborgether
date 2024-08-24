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

import com.axy.mapper.DemandMapper;
import com.axy.mapper.UsersMapper;
import com.axy.pojo.Demand;
import com.axy.service.DemandService;

import jakarta.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/demand")

public class DemandController {
    @Resource
    private DemandService demandService;
    private final UsersMapper usersMapper;
    private DemandMapper demandMapper;
    public DemandController(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }


    public DemandController(DemandMapper demandMapper, UsersMapper usersMapper) {
        this.demandMapper = demandMapper;
        this.usersMapper = usersMapper;
    }

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
