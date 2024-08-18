package generator.service;

import generator.pojo.Announcements;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Service
* @createDate 2024-08-16 17:20:33
*/

public interface AnnouncementsService extends IService<Announcements> {
    List<Announcements> getAll();

}
