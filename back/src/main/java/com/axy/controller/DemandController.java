package com.axy.controller;

import com.axy.common.DupdateRequest;
import com.axy.mapper.DemandMapper;
import com.axy.mapper.UsersMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/demand")

public class DemandController {
    @Resource
    private DemandService demandService;
    private DemandMapper demandMapper;
    private UsersMapper usersMapper;

    public DemandController(DemandMapper demandMapper, UsersMapper usersMapper) {
        this.demandMapper = demandMapper;
        this.usersMapper = usersMapper;
    }

    @GetMapping("/list")
    //展示投诉建议
    public List<Demand> list(){
        return demandService.list();
    }

    //添加投诉
    @PostMapping("/addC")
    public Map<String,Object> addC(@RequestBody DupdateRequest dupdateRequest){
        Map<String,Object> response=new HashMap<>();
        String phonenumber=dupdateRequest.getPhonenumber();
        int id=usersMapper.findid(phonenumber);
        Demand demand= dupdateRequest.getDemand();
        String demandtype="投诉";
        demand.setDemandtype(demandtype);
        String demandmsg=demand.getDemandmsg();
        demand.setUserid(id);
        demand.setDemandmsg(demandmsg);
        boolean isInserted = demandService.save(demand);
        if (isInserted) {
            response.put("status", "1");
            response.put("message", "投诉信息添加成功");
        } else {
            response.put("status", "0");
            response.put("message", "投诉信息添加失败");
        }
        return response;
    }

    //添加建议
    @PostMapping("/addG")
    public Map<String,Object> addG(@RequestBody DupdateRequest dupdateRequest){
        Map<String,Object> response=new HashMap<>();
        String phonenumber=dupdateRequest.getPhonenumber();
        int id=usersMapper.findid(phonenumber);
        Demand demand= dupdateRequest.getDemand();
        String demandtype="建议";
        String demandmsg=demand.getDemandmsg();
        demand.setDemandtype(demandtype);
        demand.setUserid(id);
        demand.setDemandmsg(demandmsg);
        boolean isInserted = demandService.save(demand);
        if (isInserted) {
            response.put("status", "1");
            response.put("message", "建议信息添加成功");
        } else {
            response.put("status", "0");
            response.put("message", "建议信息添加失败");
        }
        return response;
    }
    @DeleteMapping("/delete")
    //删除投诉建议
    public boolean delete(@RequestParam("demandid") int demandid){
        return demandService.removeById(demandid);
        
    }
}
