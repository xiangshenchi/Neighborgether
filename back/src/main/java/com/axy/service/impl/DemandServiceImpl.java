package com.axy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.axy.pojo.Demand;
import com.axy.service.DemandService;
import com.axy.mapper.DemandMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【demand】的数据库操作Service实现
* @createDate 2024-08-18 08:50:30
*/
@Service
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand>
    implements DemandService{

}



