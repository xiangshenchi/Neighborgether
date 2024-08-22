package com.axy.mapper;

import com.axy.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【users】的数据库操作Mapper
* @createDate 2024-08-17 15:41:45
* @Entity com.axy.pojo.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    List<Users> listAll();
    List<Users> listU(String phonenumber);


    String findbyphonenumber(String phonenumber);
    Boolean existsphone( String phonenumber);
    String findrole(String phonenumber);
    Users findbyphone(String phonenumber);
}




