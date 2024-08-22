package com.axy.controller;

import com.axy.pojo.Vehicles;
import com.axy.service.VehiclesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicles")

public class VehiclesController {
    @Resource
    private VehiclesService vehiclesService;
    @PostMapping("/list")
    public List<Vehicles> list() {
        return vehiclesService.list();
    }
}
