package com.axy.mapper;

import com.axy.pojo.Vehicles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【vehicles】的数据库操作Mapper
* @createDate 2024-08-18 08:50:46
* @Entity com.axy.pojo.Vehicles
*/
@Mapper
public interface VehiclesMapper extends BaseMapper<Vehicles> {



    List<Vehicles> listC(int id);

    Vehicles findbyid(Integer id);

    Vehicles getbyvid(int vehicleid);
}




