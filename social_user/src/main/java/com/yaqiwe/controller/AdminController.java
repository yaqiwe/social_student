package com.yaqiwe.controller;

import com.yaqiwe.entity.Admin;
import com.yaqiwe.service.AdminService;
import entity.Resoult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;
import util.ResoultUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 11:31
 * @Version 1.0
 */
@RequestMapping("/admin")
@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping
    public Resoult save(@RequestBody Admin admin){
        adminService.send(admin);
        return ResoultUtil.success("添加管理员成功");
    }

    @GetMapping
    public Resoult findAll(){
        return ResoultUtil.success(adminService.findAll());
    }

    @GetMapping("/{adminId}")
    public Resoult findById(@PathVariable String adminId){
        return ResoultUtil.success(adminService.findById(adminId));
    }

    @DeleteMapping("/{adminId}")
    public Resoult deleteById(@PathVariable String adminId){
        adminService.deleteById(adminId);
        return ResoultUtil.success("删除管理员成功");
    }

    @PostMapping("/login")
    public Resoult login(@RequestBody Map<String,String> loginInfo){
        Admin admin = adminService.logIn(loginInfo.get("loginname"), loginInfo.get("password"));
        String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("roles","admin");
        return ResoultUtil.success(map);
    }
}
