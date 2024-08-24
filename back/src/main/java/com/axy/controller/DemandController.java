package com.axy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axy.mapper.DemandMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.common.DupdateRequest;
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

    public DemandController(UsersMapper usersMapper, DemandMapper demandMapper) {
        this.usersMapper = usersMapper;
        this.demandMapper = demandMapper;
    }

    @GetMapping("/list")
    //展示投诉
    public List<Demand> list(){
        return demandMapper.listall();
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
    //管理员编辑投诉
    @PostMapping("/edit")
    public Map<String, Object> adedit(@RequestBody Demand demand){
        Map<String, Object> response = new HashMap<>();
        int demandid=demand.getDemandid();
        String status= (String) demand.getStatus();
        Demand de=demandMapper.getbyphonenumber(demandid);
        de.setStatus(status);
        boolean isUpdated = demandService.updateById(de);
        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "投诉信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "投诉信息修改失败");
        }
        return response;
    }
}
