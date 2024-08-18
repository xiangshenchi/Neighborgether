package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.pojo.Announcements;
import generator.service.AnnouncementsService;
import generator.mapper.AnnouncementsMapper;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Service实现
* @createDate 2024-08-16 17:20:33
*/
@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements>
    implements AnnouncementsService{
    @Resource
    private  AnnouncementsMapper announcementsMapper;
    @Override
    public List<Announcements> getAll() {
        return announcementsMapper.getAll();
    }
}




