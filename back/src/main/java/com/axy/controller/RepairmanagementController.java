package com.axy.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axy.pojo.Livingpayment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.mapper.RepairmanagementMapper;
import com.axy.pojo.Repairmanagement;
import com.axy.service.RepairmanagementService;

import jakarta.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/repairmanagement")

public class RepairmanagementController {
    @Resource
    private RepairmanagementService repairmanagementService;
    private final RepairmanagementMapper repairmanagementMapper;

    public RepairmanagementController(RepairmanagementMapper repairmanagementMapper) {
        this.repairmanagementMapper = repairmanagementMapper;
    }

    // 添加报修信息
    @PostMapping("/addR")
    public Map<String, Object> addR(@RequestBody Repairmanagement repairmanagement) {
        Map<String, Object> response = new HashMap<>();
        String repairphone = repairmanagement.getRepairphone();
        String repaircontent = repairmanagement.getRepaircontent();
        //String repairstatus="待处理";
        Repairmanagement repairmanagement1 = new Repairmanagement();
        repairmanagement1.setRepairphone(repairphone);
        repairmanagement1.setRepaircontent(repaircontent);
        //repairmanagement1.setRepairstatus(repairstatus);
        repairmanagement1.setRepairdate(new Date());
        boolean isInserted = repairmanagementService.save(repairmanagement1);
        if (isInserted) {
            response.put("status", "1");
            response.put("message", "报修信息添加成功");
        } else {
            response.put("status", "0");
            response.put("message", "报修信息添加失败");
        }
        return response;
    }
    //展示单个用户所有报修信息
    @GetMapping("/listR")
    public List<Repairmanagement> listR(@RequestParam String phonenumber){
        List<Repairmanagement> repairList = repairmanagementMapper.listbyph(phonenumber);
        if (repairList == null || repairList.isEmpty()) {
            return null;
        } else {
            return repairmanagementMapper.listbyph(phonenumber);
        }
    }
    //展示所有报修信息
    @GetMapping("/list")
    public List<Repairmanagement> list() {
        return repairmanagementService.list();
    }
    //管理员编辑报修信息
    @PostMapping("/edit")
    public Map<String, Object> adedit(@RequestBody Repairmanagement repairmanagement){
        Map<String, Object> response = new HashMap<>();
        int repairid = repairmanagement.getRepairid();
        String repairstatus = (String) repairmanagement.getRepairstatus();
        Repairmanagement re=repairmanagementMapper.getbyphonenumber(repairid);
        re.setRepairstatus(repairstatus);
        boolean isUpdated = repairmanagementService.updateById(re);
        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "报修信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "报修信息修改失败");
        }
        return response;
    }
}
