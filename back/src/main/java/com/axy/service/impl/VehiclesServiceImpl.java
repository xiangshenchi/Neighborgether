package com.axy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.axy.pojo.Vehicles;
import com.axy.service.VehiclesService;
import com.axy.mapper.VehiclesMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【vehicles】的数据库操作Service实现
* @createDate 2024-08-18 08:50:46
*/
@Service
public class VehiclesServiceImpl extends ServiceImpl<VehiclesMapper, Vehicles>
    implements VehiclesService{

}




