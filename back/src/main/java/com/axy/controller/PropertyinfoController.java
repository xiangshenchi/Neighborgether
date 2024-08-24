package com.axy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.common.UpdateRequest;
import com.axy.mapper.PropertyinfoMapper;
import com.axy.mapper.UsersMapper;
import com.axy.pojo.Propertyinfo;
import com.axy.pojo.Users;
import com.axy.service.PropertyinfoService;

import jakarta.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/propertyinfo")
public class PropertyinfoController {
    @Resource
    private PropertyinfoService propertyinfoService;
    private final PropertyinfoMapper propertyinfoMapper;
    private final UsersMapper usersMapper;
    @Autowired
    public PropertyinfoController(PropertyinfoMapper propertyinfoMapper, UsersMapper usersMapper, Users userss) {
        this.propertyinfoMapper = propertyinfoMapper;
        this.usersMapper = usersMapper;
    }

    //展示所有房产信息
   @GetMapping("/list")
    public List<Propertyinfo> list() {
        return propertyinfoService.list();
    }

    //展示单个业主的房产信息
    @GetMapping("/listP")
    public List<Propertyinfo> listU(@RequestParam String phonenumber){
        int id=usersMapper.findid(phonenumber);
        System.out.println(id);
        List<Propertyinfo> propertyList = propertyinfoMapper.listP(id);
        System.out.println(propertyList);
        if (propertyList == null || propertyList.isEmpty()) {
            return null;
        } else {
            return propertyinfoMapper.listP(id);
        }
    }
    //修改房产信息
    @PostMapping("/update")
    public Map<String, Object> update(  @RequestBody UpdateRequest request) {
        Map<String, Object> response = new HashMap<>();
        String phonenumber = request.getPhonenumber();
        Propertyinfo propertyinfo = request.getPropertyinfo();
        String roomnumber = propertyinfo.getRoomnumber();
        String buildingnumber = propertyinfo.getBuildingnumber();
        String unitnumber = propertyinfo.getUnitnumber();
        double area = propertyinfo.getArea();
        // 调用UserService的方法来更新用户信息
        //boolean isUpdated = userService.updateUser(username, email, address);
//        System.out.println(phonenumber);
//        int id = usersMapper.findid(phonenumber);
//        System.out.println(id);
        Propertyinfo propertyinfo1 = propertyinfoMapper.findbyid(usersMapper.findid(phonenumber));
//        System.out.println(propertyinfo1);
        propertyinfo1.setRoomnumber(roomnumber);
        propertyinfo1.setBuildingnumber(buildingnumber);
        propertyinfo1.setUnitnumber(unitnumber);
        propertyinfo1.setArea(area);
        boolean isUpdated = propertyinfoService.updateById(propertyinfo1);
        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "房产信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "房产信息修改失败");
        }

        // 返回更新结果
        return response;
    }


}
