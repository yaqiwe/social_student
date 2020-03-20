package com.yaqiwe.service;

import com.yaqiwe.entity.User;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 17:07
 * @Version 1.0
 */
public interface UserService {

    /**
     * 发送短信验证码
     * @param mobile
     */
    void sendSms(String mobile);

    /**
     * 用户注册
     * @param user
     */
    void save(User user);

    /**
     * 查询所有用户列表
     * @return
     */
    List<User> findAll();

    /**
     * 用户登录
     * @param mobile
     * @param password
     */
    User logIn(String mobile, String password);

    /**
     * 用户注册
     * @param code
     * @param user
     */
    void register(String code, User user);

    /**
     * 根据Id查找用户信息
     * @param userId
     * @return
     */
    User findById(String userId);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteById(String userId);
}
