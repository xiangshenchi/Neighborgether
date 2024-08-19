package com.axy.controller;

import com.axy.mapper.VisitorsMapper;
import com.axy.pojo.Visitors;
import com.axy.service.VisitorsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/visitors")
public class VisitorsController {
    @Resource
    private VisitorsService visitorsService;
    private VisitorsMapper visitorsMapper;

    public VisitorsController(VisitorsMapper visitorsMapper) {
        this.visitorsMapper = visitorsMapper;
    }


    // 添加访客
    @PostMapping("/save")
    public Map<String, Object> save(@RequestParam("visitname") String visitname, @RequestParam("sex") String sex, @RequestParam("visitphone") String visitphone, @RequestParam("visitreason") String visitreason) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 调用服务层根据手机号查询访客信息
            Visitors existingVisitor = visitorsMapper.findByVisitphone(visitphone);
            if (existingVisitor != null) {
                // 获取当前日期和时间
                LocalDateTime now = LocalDateTime.now();
                // 获取访客的登记日期和时间
                LocalDateTime visitDateTime = visitorsMapper.getVisitDateTime(visitphone);
                // 判断是否超过24小时
                if (now.isAfter(visitDateTime.plusHours(24))) {
                    // 超过24小时，允许登记
                    saveVisitor(visitname, sex, visitphone, visitreason, result);
                } else {
                    // 未超过24小时，不允许登记
                    result.put("status", 0);
                    result.put("message", "该手机号在24小时内已登记，不允许重复登记");
                }
            } else {
                // 没有找到对应的访客信息，允许登记
                saveVisitor(visitname, sex, visitphone, visitreason, result);
            }
        } catch (Exception e) {
            result.put("status", 0);
            result.put("message", "访客信息保存失败: " + e.getMessage());
        }
        return result;
    }

    private void saveVisitor(String visitname, String sex, String visitphone, String visitreason, Map<String, Object> result) {
        Visitors visitors = new Visitors();
        visitors.setVisitname(visitname);
        visitors.setSex(sex);
        visitors.setVisitphone(visitphone);
        visitors.setVisitreason(visitreason);
        boolean saveSuccess = visitorsService.save(visitors);
        if (saveSuccess) {
            result.put("status", 1);
            //result.put("message", "访客信息保存成功");
        } else {
            result.put("status", 0);
            result.put("message", "访客信息保存失败");
        }
    }

    // 查询所有访客信息
    @GetMapping("/list")
    public List<Visitors> listVisitors() {
        return visitorsService.list();
    }
}
