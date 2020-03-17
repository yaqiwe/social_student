package com.yaqiwe.repository;

import com.yaqiwe.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 18:04
 * @Version 1.0
 */
public interface LabelRepository extends JpaRepository<Label,String> , JpaSpecificationExecutor<Label> {

}
