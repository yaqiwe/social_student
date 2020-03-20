package com.yaqiwe.service.impl;

import com.yaqiwe.entity.User;
import com.yaqiwe.exception.UserException;
import com.yaqiwe.repository.UserRepository;
import com.yaqiwe.service.UserService;
import io.jsonwebtoken.Claims;
import jdk.nashorn.internal.ir.IfNode;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.JwtUtil;
import util.SnowflakeUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 17:10
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SnowflakeUtil snowflakeUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    HttpServletRequest request;


    @Override
    public void sendSms(String mobile) {
        /*随机生成6位随机数*/
        String checkcode = RandomStringUtils.randomNumeric(6);
        /*放入缓存中*/
        redisTemplate.delete("sendSms_"+mobile);
        redisTemplate.opsForValue().set("sendSms_"+mobile,checkcode,6, TimeUnit.HOURS);
        /*发送到消息队列,通知短信服务个用户发送验证码*/
        Map<String,String > smsMap=new HashMap<>();
        smsMap.put("checkcode",checkcode);
        smsMap.put("mobile",mobile);
        rabbitTemplate.convertAndSend("sms",smsMap);
        log.info("sendSms 验证码: {}",checkcode);
    }

    @Override
    public void save(User user) {
        User byMobile = userRepository.findByMobile(user.getMobile());
        if(byMobile!=null){
            throw new UserException("该手机号已注册");
        }
        else {
            user.setId(snowflakeUtil.nextId()+"");
            Date data=new Date();
            Timestamp timestamp=new Timestamp(data.getTime());
            user.setRegdate(timestamp);
            user.setOnline(0L);
            user.setFanscount(0);
            user.setPassword(encoder.encode(user.getPassword()+user.getId()));
            user.setFollowcount(0);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User logIn(String mobile, String password) {
        User byMobile = userRepository.findByMobile(mobile);
        if(byMobile!=null){
            boolean matches = encoder.matches(password + byMobile.getId(), byMobile.getPassword());
            if(matches){
                return byMobile;
            }
        }
        throw new UserException("用户名或密码错误");
    }

    @Override
    public void register(String code, User user) {
        String checkcode= (String) redisTemplate.opsForValue().get("sendSms_"+user.getMobile());
       if(checkcode==null){
           throw new UserException("请先获取验证码");
       }else if(!checkcode.equals(code)){
           throw new UserException("验证码错误");
       }else{
           save(user);
           redisTemplate.delete("sendSms_"+user.getMobile());
       }
    }

    @Override
    public User findById(String userId) {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public void update(User user) {
        //TODO
    }

    @Override
    public void deleteById(String userId) {
        String token = (String) request.getAttribute("claims_admin");
        if (token==null){
            throw new UserException("权限不足");
        }
        userRepository.deleteById(userId);
    }

}
