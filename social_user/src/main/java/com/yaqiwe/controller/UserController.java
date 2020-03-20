package com.yaqiwe.controller;

import com.yaqiwe.entity.User;
import com.yaqiwe.service.UserService;
import entity.Resoult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;
import util.ResoultUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 19:28
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping
    public Resoult save(@RequestBody User user){
        userService.save(user);
        return ResoultUtil.success("添加用户成功");
    }

    @GetMapping
    public Resoult findAll(){
        return ResoultUtil.success(userService.findAll());
    }

    @PostMapping("/login")
    public Resoult logIn(@RequestBody Map<String ,String> loginInfo){
        User user = userService.logIn(loginInfo.get("mobile"), loginInfo.get("password"));
        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("roles","user");
        return ResoultUtil.success(map);
    }

    @PostMapping("/register/{code}")
    public Resoult register(@PathVariable String code,@RequestBody User user){
        userService.register(code,user);
        return ResoultUtil.success("用户注册成功");
    }

    @GetMapping("/{userId}")
    public Resoult findById(@PathVariable String userId){
        return ResoultUtil.success(userService.findById(userId));
    }

    @PutMapping("/{userId}")
    public Resoult update(@PathVariable String userId,@RequestBody User user){
        user.setId(userId);
        userService.update(user);
        return ResoultUtil.success("更新用户信息成功");
    }

    @DeleteMapping("/{userId}")
    public Resoult deleteById(@PathVariable String userId){
        userService.deleteById(userId);
        return ResoultUtil.success("删除用户成功");
    }

    @PostMapping("/sendsms/{mobile}")
    public Resoult sendsms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return ResoultUtil.success("验证码已发送");
    }
}
