package com.axy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axy.pojo.Users;
import com.axy.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import jakarta.annotation.Resource;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.axy.pojo.Users; // 假设Users类的包路径

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersController {
    @GetMapping()
    public String hello() {
        return "Hello yuasang!!!";
    }
    @Resource
//    @Autowired
    private UsersService userService;
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
    public Map<String, Object> save(@RequestParam("phonenumber") String phonenumber  , @RequestParam("password") String password) {
        Map<String, Object> result = new HashMap<>();

        // 判断手机号是否合法
        if (phonenumber == null || phonenumber.length() != 11) {
            result.put("status", false);
            result.put("message", "手机号不合法");
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
            result.put("status", false);
            result.put("message", "保存过程中发生错误: " + e.getMessage());
        }

        return result;
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Users users){
        return userService.updateById(users);
    }
    //新增或修改
    @PostMapping("/saveOrmod")
    public boolean saveOrmod(@RequestBody Users users){
        return userService.saveOrUpdate(users);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(@RequestParam int userid){
        return userService.removeById(userid);
    }
    //模糊查询
    @PostMapping("/listP")
    public List<Users> listP(@RequestBody Users users){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Users::getUsername, users.getUsername());
        return userService.list(wrapper);
    }

}