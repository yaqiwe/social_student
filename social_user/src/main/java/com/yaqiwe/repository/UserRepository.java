package com.yaqiwe.repository;

import com.yaqiwe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 19:27
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,String> , JpaSpecificationExecutor<User> {

    User findByMobile(String mobile);
}
