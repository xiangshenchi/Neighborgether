package com.axy.mapper;

import com.axy.pojo.Demand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author DELL
* @description 针对表【demand】的数据库操作Mapper
* @createDate 2024-08-18 08:50:30
* @Entity com.axy.pojo.Demand
*/
@Mapper
public interface DemandMapper extends BaseMapper<Demand> {
    
    //展示投诉建议
    List<Demand> list();
    
    //增加投诉建议
     boolean add(@RequestBody Demand demand);
    
    //删除投诉建议
    boolean delete(@RequestParam("demandid") int demandid);

}




