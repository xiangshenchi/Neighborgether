package com.axy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.axy.pojo.Propertyinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DELL
* @description 针对表【propertyinfo】的数据库操作Mapper
* @createDate 2024-08-18 08:50:37
* @Entity com.axy.pojo.Propertyinfo
*/
@Mapper
public interface PropertyinfoMapper extends BaseMapper<Propertyinfo> {

    List<Propertyinfo> listP(int userid);
    Propertyinfo findbyid(Integer id);

    Propertyinfo getbyid(int propertyid);
}




