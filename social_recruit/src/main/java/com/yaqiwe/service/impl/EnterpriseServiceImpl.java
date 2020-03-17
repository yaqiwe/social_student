package com.yaqiwe.service.impl;

import com.yaqiwe.entity.Enterprise;
import com.yaqiwe.exception.RecruitException;
import com.yaqiwe.repository.EnterpriseRepository;
import com.yaqiwe.service.EnterpriseService;
import entity.StatusCode;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
 * @Date 2020/3/16 21:19
 * @Version 1.0
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    EnterpriseRepository enterpriseR;

    @Autowired
    SnowflakeUtil snowflakeU;

    @Override
    public void save(Enterprise enterprise) {
        enterprise.setId(snowflakeU.nextId()+"");
        enterpriseR.save(enterprise);
    }

    @Override
    public List<Enterprise> findAll() {
        return enterpriseR.findAll();
    }

    @Override
    public Enterprise findById(String Id) {
        Optional<Enterprise> byId = enterpriseR.findById(Id);
        if(byId!=null)
            return byId.get();
        return null;
    }

    @Override
    public void update(Enterprise enterprise) {
        if (enterprise.getId()!=null && !enterprise.getId().isEmpty()) {
            enterpriseR.save(enterprise);
        }else {
            throw new RecruitException(StatusCode.ENTERPRISE_NULL);
        }
    }

    @Override
    public void deleteById(String id) {
        enterpriseR.deleteById(id);
    }

    @Override
    public List<Enterprise> findSearch(Enterprise enterprise) {
        return enterpriseR.findAll(getSearchCondition(enterprise));
    }

    @Override
    public List<Enterprise> hotList() {
        return enterpriseR.findByIshot("1");
    }

    @Override
    public Page<Enterprise> pageQuery(Enterprise enterprise, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return enterpriseR.findAll(getSearchCondition(enterprise),pageable);
    }

    /**获取条件查询的对象*/
    private Specification<Enterprise> getSearchCondition(Enterprise enterprise){
        Specification<Enterprise> specification = new Specification<Enterprise>() {

            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if (enterprise.getName()!=null && !enterprise.getName().isEmpty()) {
                    Predicate labelName = criteriaBuilder.like(root.get("name").as(String.class), "%"+enterprise.getName()+"%");
                    predicateList.add(labelName);
                }
                if (enterprise.getAddress()!=null && !enterprise.getAddress().isEmpty()){
                    Predicate state = criteriaBuilder.like(root.get("address").as(String.class), "%"+enterprise.getAddress()+"%");
                    predicateList.add(state);
                }
                Predicate[] and = new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(and));
            }
        };
        return specification;
    }
}
