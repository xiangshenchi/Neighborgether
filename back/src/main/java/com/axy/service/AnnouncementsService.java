package com.axy.service;

import com.axy.pojo.Announcements;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DELL
* @description 针对表【announcements】的数据库操作Service
* @createDate 2024-08-18 08:50:22
*/
public interface AnnouncementsService extends IService<Announcements> {

    IPage pageC(Page<Announcements> page);
}
