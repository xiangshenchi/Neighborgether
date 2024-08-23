package com.axy.controller;

import com.axy.common.UpdateRequest;
import com.axy.mapper.PropertyinfoMapper;
import com.axy.mapper.UsersMapper;
import com.axy.pojo.Propertyinfo;
import com.axy.pojo.Users;
import com.axy.service.PropertyinfoService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/propertyinfo")
public class PropertyinfoController {
    @Resource
    private PropertyinfoService propertyinfoService;
    private PropertyinfoMapper propertyinfoMapper;
    private UsersMapper usersMapper;
    @Autowired
     private Users userss;
    @Autowired
    public PropertyinfoController(PropertyinfoMapper propertyinfoMapper, UsersMapper usersMapper, Users userss) {
        this.propertyinfoMapper = propertyinfoMapper;
        this.usersMapper = usersMapper;
        this.userss = userss;
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
        //System.out.println(id);
        List<Propertyinfo> propertyList = propertyinfoMapper.listP(id);
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
        int id=usersMapper.findid(phonenumber);
        List<Propertyinfo> propertyList = propertyinfoMapper.listP(id);
        String roomnumber;
        String buildingnumber;
        String unitnumber;
        double area;
        if (propertyList == null || propertyList.isEmpty()) {
            Propertyinfo newProperty = request.getPropertyinfo();
            roomnumber = newProperty.getRoomnumber();
            buildingnumber = newProperty.getBuildingnumber();
            unitnumber = newProperty.getUnitnumber();
            area = newProperty.getArea();
            newProperty.setUserid(id);
            newProperty.setRoomnumber(roomnumber);
            newProperty.setBuildingnumber(buildingnumber);
            newProperty.setUnitnumber(unitnumber);
            newProperty.setArea(area);
            boolean isInserted = propertyinfoService.save(newProperty);
            if (isInserted) {
                response.put("status", "1");
                response.put("message", "房产信息添加成功");
            } else {
                response.put("status", "0");
                response.put("message", "房产信息添加失败");
            }

        } else {
            Propertyinfo propertyinfo = request.getPropertyinfo();
             roomnumber = propertyinfo.getRoomnumber();
             buildingnumber = propertyinfo.getBuildingnumber();
             unitnumber = propertyinfo.getUnitnumber();
             area = propertyinfo.getArea();
            Propertyinfo propertyinfo1 = propertyinfoMapper.findbyid(usersMapper.findid(phonenumber));

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
        }

        return response;
    }


}
