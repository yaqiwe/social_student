package com.yaqiwe.service.impl;

import com.yaqiwe.entity.Label;
import com.yaqiwe.repository.LabelRepository;
import com.yaqiwe.service.LanelService;
import lombok.extern.slf4j.Slf4j;
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
 * @Date 2020/3/15 18:22
 * @Version 1.0
 */
@Service
@Slf4j
public class LanelServiceImpl implements LanelService {
    @Autowired
    LabelRepository labelR;

    @Autowired
    SnowflakeUtil snowflakeU;

    @Override
    public List<Label> findAll() {
        return labelR.findAll();
    }

    @Override
    public Label findById(String id) {
        Optional<Label> byId = labelR.findById(id);
        if (byId.isPresent())
            return byId.get();
        return null;
    }

    @Override
    public void save(Label label) {
        label.setId(snowflakeU.nextId()+"");
        labelR.save(label);
    }

    @Override
    public void update(Label label) {
        labelR.save(label);
    }

    @Override
    public void deleteById(String id) {
        labelR.deleteById(id);
    }

    @Override
    public List<Label> findSearch(Label label) {
        List<Label> labels = labelR.findAll(getSearchCondition(label));
        return labels;
    }

    @Override
    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        Page<Label> labels = labelR.findAll(getSearchCondition(label),pageable);
        return labels;
    }

    /**获取条件查询的对象*/
    private Specification<Label> getSearchCondition(Label label){
        Specification<Label> specification = new Specification<Label>() {

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if (label.getLabelName()!=null && !label.getLabelName().isEmpty()) {
                    Predicate labelName = criteriaBuilder.like(root.get("labelName").as(String.class), "%"+label.getLabelName()+"%");
                    predicateList.add(labelName);
                }
                if (label.getState()!=null && !label.getState().isEmpty()){
                    Predicate state = criteriaBuilder.like(root.get("state").as(String.class), label.getState());
                    predicateList.add(state);
                }
                Predicate[] and = new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(and));
            }
        };
        return specification;
    }

}
