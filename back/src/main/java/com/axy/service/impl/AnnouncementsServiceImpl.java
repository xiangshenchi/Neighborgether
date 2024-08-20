package com.axy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.axy.pojo.Announcements;
import com.axy.service.AnnouncementsService;
import com.axy.mapper.AnnouncementsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Service实现
* @createDate 2024-08-18 08:50:22
*/
@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements>
    implements AnnouncementsService{


}




