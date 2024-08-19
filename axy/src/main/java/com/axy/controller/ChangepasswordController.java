package com.axy.controller;

import com.axy.pojo.Changepassword;
import com.axy.service.ChangepasswordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/changepassword")


public class ChangepasswordController {
    @Resource
    private ChangepasswordService changepasswordService;
    //修改密码
    @PostMapping("/updatepassword")
    public Map<String, Object> changepassword(@RequestParam("changephone") String changephone, @RequestParam("newpassword") String newpassword) {
        Map<String, Object> response = new HashMap<>();

        // 检查手机号是否为11位
        if (changephone.length() != 11) {
            response.put("status", 0);
            response.put("message", "手机号必须为11位。");
            return response;
        }

        // 检查密码是否含有空格
        if (newpassword.contains(" ")) {
            response.put("status", 0);
            response.put("message", "密码不能含有空格。");
            return response;
        }

        // 假设ChangepasswordService有一个方法savePassword来保存数据
        Changepassword changepassword= new Changepassword();
        changepassword.setChangephone(changephone);
        changepassword.setNewpassword(newpassword);
        boolean saveSuccessful = changepasswordService.save(changepassword);
        if (saveSuccessful) {
            response.put("status", 1);
            response.put("message", "密码修改成功。");
        } else {
            response.put("status", 0);
            response.put("message", "密码修改失败，数据库操作出现错误。");
        }

        return response;
    }

}
