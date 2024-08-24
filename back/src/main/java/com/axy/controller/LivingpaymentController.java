package com.axy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.common.LupdateRequest;
import com.axy.mapper.LivingpaymentMapper;
import com.axy.mapper.UsersMapper;
import com.axy.pojo.Livingpayment;
import com.axy.service.LivingpaymentService;

import jakarta.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/livingpayment")

public class LivingpaymentController {
    private final UsersMapper usersMapper;
    private final LivingpaymentMapper livingpaymentMapper;
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
        Livingpayment livingpayment=request.getLivingpayment();
        int paymentid = livingpayment.getPaymentid();
        int userid=usersMapper.findid(phonenumber);
        String status="已缴";
         Livingpayment list=livingpaymentMapper.listD(userid,paymentid);
        //System.out.println(list);
        list.setStatus(status);
            boolean isUpdated = liv.updateById(list);
            if (isUpdated) {
                response.put("status", "1");
                response.put("message", "缴费状态修改成功");
            } else {
                response.put("status", "0");
                response.put("message", "缴费状态修改失败");
            }

        return response;
    }
}
