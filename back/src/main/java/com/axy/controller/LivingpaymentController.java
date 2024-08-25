package com.axy.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axy.service.UsersService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private UsersService usersService;

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
    @DeleteMapping("/delete")
    //管理员删除缴费信息
    public boolean delete(@RequestParam int paymentid) {
        return liv.removeById(paymentid);
    }
    //管理员添加缴费信息
    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Livingpayment livingpayment) {
        Map<String, Object> response = new HashMap<>();
        int userid = livingpayment.getUserid();
        // 检查userid是否在user表中存在
        if (usersMapper.isUserExists(userid)==null) {
            response.put("status", "0");
            response.put("message", "用户ID不存在，缴费信息添加失败");
            return response;
        }
        String paymenttype = (String) livingpayment.getPaymenttype();
        BigDecimal amount = livingpayment.getAmount();
        livingpayment.setUserid(userid);
        livingpayment.setPaymenttype(paymenttype);
        livingpayment.setUserid(userid);
        livingpayment.setAmount(amount);
        livingpayment.setStatus("未缴"); 
        boolean isSaved = liv.save(livingpayment);
        if (isSaved) {
            response.put("status", "1");
            response.put("message", "缴费信息添加成功");
        } else {
            response.put("status", "0");
            response.put("message", "缴费信息添加失败");
        }
        return response;
    }
    //管理员编辑缴费信息
    @PostMapping("/edit")
    public Map<String, Object> adedit(@RequestBody Livingpayment livingpayment){
        Map<String, Object> response = new HashMap<>();
        int paymentid =livingpayment.getPaymentid();
        String paymenttype = (String) livingpayment.getPaymenttype();
        BigDecimal amount = livingpayment.getAmount();
        Livingpayment li=livingpaymentMapper.getbyphonenumber(paymentid);
        li.setPaymenttype(paymenttype);
        li.setAmount(amount);
        boolean isUpdated = liv.updateById(li);
        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "公告信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "公告信息修改失败");
        }
        return response;
    }
    //展示所有缴费信息
    @GetMapping("/list")
    public List<Livingpayment> list() {
        return liv.list();
    }
}
