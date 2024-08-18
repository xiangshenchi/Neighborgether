package com.axy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.axy.pojo.Users;
import com.axy.service.UsersService;
import com.axy.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author DELL
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-08-17 15:41:45
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{
    @Resource//或者使用@Autowired，有几率爆红
    private UsersMapper userMapper;
    private UsersMapper userMapper1;

    @Override
    //查询所有用户信息
    public List<Users> listAll() {
        return userMapper.listAll();
    }


    @Override
    public int showaccount(String phonenumber, String password){
        String pw=userMapper.findbyphonenumber(phonenumber);
        //账号不存在，返回0
        if(pw==null){
            return 0;
        }
        //密码错误，返回1
        if(!pw.equals(password)){
            return 1;
        }
        //登录成功，返回2
        return 2;
    }

}




