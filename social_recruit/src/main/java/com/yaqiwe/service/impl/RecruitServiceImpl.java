package com.yaqiwe.service.impl;

import com.yaqiwe.entity.Recruit;
import com.yaqiwe.repository.RecruitRepository;
import com.yaqiwe.service.RecruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.SnowflakeUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author yaqiwe
 * @Date 2020/3/17 10:00
 * @Version 1.0
 */
@Service
@Slf4j
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    RecruitRepository recruitR;

    @Autowired
    SnowflakeUtil snowflakeU;

    @Override
    public void save(Recruit recruit) {
        recruit.setId(snowflakeU.nextId() + "");
        recruitR.save(recruit);
    }

    @Override
    public List<Recruit> findAll() {
        return recruitR.findAll();
    }

    @Override
    public Recruit findById(String id) {
        Optional<Recruit> byId = recruitR.findById(id);
        if (byId != null) {
            return byId.get();
        }
        return null;
    }

    @Override
    public void update(Recruit recruit) {
        Recruit recruit1 = findById(recruit.getId());
        recruitR.save(recruit);
    }

    @Override
    public void deleteById(String recruitId) {
        recruitR.deleteById(recruitId);
    }

    @Override
    public List<Recruit> findSearch(Recruit recruit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return recruitR.findAll(getSearchCondition(recruit), sort);
    }

    @Override
    public Page<Recruit> pageQuery(Recruit recruit, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return recruitR.findAll(getSearchCondition(recruit), pageable);
    }

    @Override
    public List<Recruit> recommend() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return recruitR.findByStateNot("2",sort);
    }

    @Override
    public List<Recruit> newList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return recruitR.findByState("0", sort);
    }

    /**
     * 获取条件查询的对象
     */
    private Specification<Recruit> getSearchCondition(Recruit recruit) {
        Specification<Recruit> specification = new Specification<Recruit>() {

            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (recruit.getJobName() != null && !recruit.getJobName().isEmpty()) {
                    Predicate labelName = criteriaBuilder.like(root.get("jobName").as(String.class), "%" + recruit.getJobName() + "%");
                    predicateList.add(labelName);
                }
//                if (recruit.getAddress()!=null && !recruit.getAddress().isEmpty()){
//                    Predicate state = criteriaBuilder.like(root.get("address").as(String.class), "%"+recruit.getAddress()+"%");
//                    predicateList.add(state);
//                }
                Predicate[] and = new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(and));
            }
        };
        return specification;
    }
}
