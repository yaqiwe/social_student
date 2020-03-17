package com.yaqiwe.repository;

import com.yaqiwe.entity.Recruit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 21:10
 * @Version 1.0
 */
public interface RecruitRepository extends JpaRepository<Recruit,String> , JpaSpecificationExecutor<Recruit> {

    List<Recruit> findByState(String state, Sort sort);

    List<Recruit> findByStateNot(String state,Sort sort);
}
