package com.axy.mapper;

import com.axy.pojo.Visitors;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
* @author DELL
* @description 针对表【visitors】的数据库操作Mapper
* @createDate 2024-08-18 15:45:45
* @Entity com.axy.pojo.Visitors
*/
@Mapper
public interface VisitorsMapper extends BaseMapper<Visitors> {
    Visitors findByVisitphone(String visitphone);
    LocalDateTime getVisitDateTime(String visitphone);
}




