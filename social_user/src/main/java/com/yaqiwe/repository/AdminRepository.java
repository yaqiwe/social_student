package com.yaqiwe.repository;

import com.yaqiwe.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 11:18
 * @Version 1.0
 */
public interface AdminRepository extends JpaRepository<Admin,String> , JpaSpecificationExecutor<Admin> {

    Admin findByLoginname(String loginname);
}
