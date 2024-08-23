package com.axy.controller;

import com.axy.mapper.UsersMapper;
import com.axy.mapper.VehiclesMapper;
import com.axy.pojo.Vehicles;
import com.axy.service.VehiclesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicles")

public class VehiclesController {
    @Resource
    private VehiclesService vehiclesService;
    private VehiclesMapper vehiclesMapper;
    private UsersMapper usersMapper;

    public VehiclesController(VehiclesMapper vehiclesMapper, UsersMapper usersMapper) {
        this.vehiclesMapper = vehiclesMapper;
        this.usersMapper = usersMapper;
    }

    //展示所有车辆信息
    @PostMapping("/list")
    public List<Vehicles> list() {
        return vehiclesService.list();
    }
    //展示指定用户的车辆信息
    @GetMapping("/listC")
    public List<Vehicles> listV(@RequestParam String phonenumber){
        int id=usersMapper.findid(phonenumber);
        System.out.println(id);
        return vehiclesMapper.listC(id);
    }
}
