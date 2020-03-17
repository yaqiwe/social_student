package com.yaqiwe.repository;

import com.yaqiwe.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 21:08
 * @Version 1.0
 */

public interface EnterpriseRepository extends JpaRepository<Enterprise,String> , JpaSpecificationExecutor<Enterprise> {

    List<Enterprise> findByIshot(String ishot);
}
