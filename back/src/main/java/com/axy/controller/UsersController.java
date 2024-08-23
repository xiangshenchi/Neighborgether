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

import com.axy.mapper.UsersMapper;
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
    private final UsersMapper usersMapper;
    //展示所有用户信息
    @GetMapping("/list")
    public List<Users> list() {//        return userService.list();
        return userService.listAll();
    }
    //展示单个用户信息
    @GetMapping("/listU")
    public List<Users> listU(@RequestParam String phonenumber){
        return usersMapper.listU(phonenumber);
    }

    //登录
    @GetMapping("/showaccount")
    public Map<String,Object> showaccount(@RequestParam String phonenumber, @RequestParam String password){
        Map<String,Object> map=new HashMap<>();
        map.put("status",userService.showaccount(phonenumber,password));
        map.put("role",usersMapper.findrole(phonenumber));
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

    // 修改信息
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Users users) {
        Map<String, Object> response = new HashMap<>();
        String phonenumber = users.getPhonenumber();
        String username = users.getUsername();
        String email = users.getEmail();
        String address = users.getAddress();
        // 调用UserService的方法来更新用户信息
        //boolean isUpdated = userService.updateUser(username, email, address);
        Users user=usersMapper.findbyphone(phonenumber);
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        boolean isUpdated = userService.updateById(user);

        if (isUpdated) {
            response.put("status", "1");
            response.put("message", "用户信息修改成功");
        } else {
            response.put("status", "0");
            response.put("message", "用户信息修改失败");
        }

        // 返回更新结果
        return response;
    }

    //模糊查询
    @PostMapping("/listP")
    public List<Users> listP(@RequestBody Users users){
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Users::getUsername, users.getUsername());
        return userService.list(wrapper);
    }

}