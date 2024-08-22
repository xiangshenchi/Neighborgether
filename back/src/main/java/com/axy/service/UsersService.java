package com.axy.service;

import com.axy.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author DELL
* @description 针对表【users】的数据库操作Service
* @createDate 2024-08-17 15:41:45
*/
public interface UsersService extends IService<Users> {
    List<Users> listAll();

    int showaccount(String phonenumber, String password);

    boolean updateUser(String username, String email, String address);
}
