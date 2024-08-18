package generator.mapper;

import generator.pojo.Announcements;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Mapper
* @createDate 2024-08-16 17:20:33
* @Entity generator.pojo.Announcements
*/

@Mapper
public interface AnnouncementsMapper extends BaseMapper<Announcements> {
    List<Announcements> getAll();

}




