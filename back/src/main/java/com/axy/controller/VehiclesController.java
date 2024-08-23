package com.axy.controller;

import com.axy.common.UpdateRequest;
import com.axy.common.VupdateRequest;
import com.axy.mapper.UsersMapper;
import com.axy.mapper.VehiclesMapper;
import com.axy.pojo.Propertyinfo;
import com.axy.pojo.Vehicles;
import com.axy.service.VehiclesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //修改车辆信息
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody VupdateRequest request) {
        Map<String, Object> response = new HashMap<>();
        String phonenumber = request.getPhonenumber();
        Vehicles vehicles = request.getVehicles();
        String licenseplate = vehicles.getLicenseplate();
        String vehicletype = vehicles.getVehicletype();
       Vehicles vehicles1 = vehiclesMapper.findbyid(usersMapper.findid(phonenumber));

//        System.out.println(propertyinfo1);
        vehicles1.setLicenseplate(licenseplate);
        vehicles1.setVehicletype(vehicletype);

        boolean isUpdated = vehiclesService.updateById(vehicles1);
        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "车辆信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "车辆信息修改失败");
        }

        // 返回更新结果
        return response;
    }

}
