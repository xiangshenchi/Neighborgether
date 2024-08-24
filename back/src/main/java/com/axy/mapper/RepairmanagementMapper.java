package com.axy.mapper;

import com.axy.pojo.Repairmanagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【repairmanagement】的数据库操作Mapper
* @createDate 2024-08-24 09:24:53
* @Entity com.axy.pojo.Repairmanagement
*/
@Mapper
public interface RepairmanagementMapper extends BaseMapper<Repairmanagement> {
    List<Repairmanagement> listbyph(String repairphone);

}




