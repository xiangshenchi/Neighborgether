package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.Demand;
import generator.service.DemandService;
import generator.mapper.DemandMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【demand】的数据库操作Service实现
* @createDate 2024-08-16 17:21:22
*/
@Service
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand>
    implements DemandService{

}




