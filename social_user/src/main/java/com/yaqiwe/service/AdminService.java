package com.yaqiwe.service;

import com.yaqiwe.entity.Admin;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 11:21
 * @Version 1.0
 */
public interface AdminService  {

    /**
     * 添加管理员
     * @param admin
     */
    void send(Admin admin);

    /**
     * 查询所有管理员列表
     * @return
     */
    List<Admin> findAll();

    /**
     * 根据Id查询管理员信息
     * @param adminId
     * @return
     */
    Admin findById(String adminId);

    /**
     * 更新管理员信息
     * @param admin
     */
    void update(Admin admin);

    /**
     * 删除管理员
     * @param adminId
     */
    void deleteById(String adminId);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    Admin logIn(String userName,String password);
}
