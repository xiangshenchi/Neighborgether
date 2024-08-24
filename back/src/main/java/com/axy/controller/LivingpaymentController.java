package com.axy.controller;

import com.axy.common.LupdateRequest;
import com.axy.common.VupdateRequest;
import com.axy.mapper.LivingpaymentMapper;
import com.axy.mapper.UsersMapper;
import com.axy.pojo.Livingpayment;
import com.axy.service.LivingpaymentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/livingpayment")

public class LivingpaymentController {
    private UsersMapper usersMapper;
    private LivingpaymentMapper livingpaymentMapper;
    @Resource
    private LivingpaymentService liv;

    public LivingpaymentController(UsersMapper usersMapper, LivingpaymentMapper livingpaymentMapper) {
        this.usersMapper = usersMapper;
        this.livingpaymentMapper = livingpaymentMapper;
    }

    //展示指定用户的生活缴费信息
    @GetMapping("/listF")
    public List<Livingpayment> listV(@RequestParam String phonenumber){
        int id=usersMapper.findid(phonenumber);
        List<Livingpayment> list=livingpaymentMapper.listC(id);
        if (list == null || list.isEmpty()) {
            return null;
        }else {
            return livingpaymentMapper.listC(id);
        }
    }
    //用户缴费
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody LupdateRequest request){
        Map<String, Object> response = new HashMap<>();
        String phonenumber = request.getPhonenumber();
        int id=usersMapper.findid(phonenumber);
        //Livingpayment livingpayment;
        List<Livingpayment> list=livingpaymentMapper.listD(id);
        System.out.println(list);
        String status="已缴";
        if (list == null || list.isEmpty()){
            response.put("status", "0");
            response.put("message", "缴费信息不存在");
        }else {
            //livingpayment=request.getLivingpayment();
            //status= (String) livingpayment.getStatus();
            Livingpayment livingpayment1=livingpaymentMapper.getById(id);
            livingpayment1.setStatus(status);
            boolean isUpdated = liv.updateById(livingpayment1);
            if (isUpdated) {
                response.put("status", "1");
                response.put("message", "缴费状态修改成功");
            } else {
                response.put("status", "0");
                response.put("message", "缴费状态修改失败");
            }
        }
        return response;
    }
}
