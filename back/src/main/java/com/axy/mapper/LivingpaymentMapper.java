package com.axy.mapper;

import com.axy.pojo.Livingpayment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【livingpayment】的数据库操作Mapper
* @createDate 2024-08-18 08:50:34
* @Entity com.axy.pojo.Livingpayment
*/
@Mapper
public interface LivingpaymentMapper extends BaseMapper<Livingpayment> {

    List<Livingpayment> listC(int id);
    List<Livingpayment> listD(int id);
    Livingpayment getById(int id);
}




