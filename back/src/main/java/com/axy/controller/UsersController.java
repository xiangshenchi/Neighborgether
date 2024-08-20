package com.axy.controller;

import com.axy.mapper.UsersMapper;
import com.axy.pojo.Users;
import com.axy.service.UsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.axy.pojo.Users; // 假设Users类的包路径

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
    public UsersController(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @GetMapping()
    public String hello() {
        return "Hello yuasang!!!";
    }
    @Resource
//    @Autowired
    private UsersService userService;
    private UsersMapper usersMapper;
    //展示所有用户信息
    @GetMapping("/list")
    public List<Users> list() {//        return userService.list();
        return userService.listAll();
    }
    //登录
    @GetMapping("/showaccount")
    public Map<String,Object> showaccount(@RequestParam String phonenumber, @RequestParam String password){
        Map<String,Object> map=new HashMap<>();
        map.put("status",userService.showaccount(phonenumber,password));
        return map;
    }
    //注册
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Users userss) {
        Map<String, Object> result = new HashMap<>();
        String phonenumber = userss.getPhonenumber();
        String password = userss.getPassword();

        // 判断手机号是否合法
        if (phonenumber == null || phonenumber.length() != 11) {
            result.put("status", 0);
            result.put("message", "手机号不合法");
            return result;
        }
        // 检查密码是否含有空格
        if (password.contains(" ")) {
            result.put("status", 0);
            result.put("message", "密码不能含有空格。");
            return result;
        }
        // 判断手机号是否已存在
        if (usersMapper.existsphone( phonenumber)!=null) {
            result.put("status", 0);
            result.put("message", "手机号已存在，无法创建");
            return result;
        }

        try {
            // 根据手机号和密码创建一个 Users 对象
            Users users = new Users();
            users.setPhonenumber(phonenumber);
            users.setPassword(password);

            boolean isSaved = userService.save(users);
            result.put("status", isSaved);
            result.put("message", isSaved ? "保存成功" : "保存失败");
        } catch (Exception e) {
            result.put("status", 0);
            result.put("message", "保存过程中发生错误: " + e.getMessage());
        }

        return result;
    }


    //模糊查询
    @PostMapping("/listP")
    public List<Users> listP(@RequestBody Users users){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Users::getUsername, users.getUsername());
        return userService.list(wrapper);
    }

}