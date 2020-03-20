package com.yaqiwe.service.impl;

import com.yaqiwe.entity.Admin;
import com.yaqiwe.exception.UserException;
import com.yaqiwe.repository.AdminRepository;
import com.yaqiwe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.SnowflakeUtil;

import java.util.List;
import java.util.Optional;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 11:26
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    SnowflakeUtil snowflakeUtil;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    AdminRepository adminR;

    @Override
    public void send(Admin admin) {
        if(admin.getLoginname()==null || admin.getLoginname().isEmpty()
            || admin.getPassword()==null || admin.getPassword().isEmpty()){
            throw new UserException("用户名和密码不能为空");
        }
        Admin byLoginname = adminR.findByLoginname(admin.getLoginname());
        if(byLoginname!=null){
            throw new UserException("用户名已存在");
        }else {
            admin.setId(snowflakeUtil.nextId()+"");
            /*用密码和Id加密后存储*/
            admin.setPassword(encoder.encode(admin.getPassword()+admin.getId()));
            adminR.save(admin);
        }
    }

    @Override
    public List<Admin> findAll() {
        return adminR.findAll();
    }

    @Override
    public Admin findById(String adminId) {
        Optional<Admin> byId = adminR.findById(adminId);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public void update(Admin admin) {
        adminR.save(admin);
    }

    @Override
    public void deleteById(String adminId) {
        adminR.deleteById(adminId);
    }

    @Override
    public Admin logIn(String userName, String password) {
        Admin admin = adminR.findByLoginname(userName);
        if(admin!=null){
            boolean matches = encoder.matches(password + admin.getId(),admin.getPassword());
            if(matches){
                return admin;
            }
        }
        throw new UserException("用户名或密码错误");
    }
}
