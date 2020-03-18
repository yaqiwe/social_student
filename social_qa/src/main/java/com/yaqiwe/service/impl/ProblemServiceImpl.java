package com.yaqiwe.service.impl;

import com.yaqiwe.entity.Problem;
import com.yaqiwe.repository.ProblemRepository;
import com.yaqiwe.service.ProblemService;
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
 * @Date 2020/3/18 14:27
 * @Version 1.0
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemR;

    @Autowired
    SnowflakeUtil snowflakeU;

    @Override
    public void save(Problem problem) {
        problem.setId(snowflakeU.nextId()+"");
        problem.setThumbUp(0L);
        problem.setSolve("0");
        problem.setVisits(0L);
        problem.setReply(0L);
        problemR.save(problem);
    }

    @Override
    public List<Problem> findAll() {
        return problemR.findAll();
    }

    @Override
    public Problem findById(String problemId) {
        Optional<Problem> byId = problemR.findById(problemId);
        if(byId!=null){
            return byId.get();
        }
        return null;
    }

    @Override
    public void update(Problem problem) {
        if(problem.getId()!=null && problem.getId().isEmpty()){
            problemR.save(problem);
        }
    }

    @Override
    public void deleteById(String problemId) {
        problemR.deleteById(problemId);
    }

    @Override
    public List<Problem> findSearch(Problem problem) {
        return problemR.findAll(getSearchCondition(problem));
    }

    @Override
    public Page<Problem> pageQuery(Problem problem, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return problemR.findAll(getSearchCondition(problem),pageable);
    }

    @Override
    public Page<Problem> newList(String labelId, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return problemR.newList(labelId,pageable);
    }

    @Override
    public Page<Problem> hotList(String labelId, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return problemR.hotList(labelId,pageable);
    }

    @Override
    public Page<Problem> witList(String labelId, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return problemR.waitList(labelId,pageable);
    }

    /**
     * 获取条件查询的对象
     */
    private Specification<Problem> getSearchCondition(Problem problem) {
        Specification<Problem> specification = new Specification<Problem>() {

            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (problem.getTitle() != null && !problem.getTitle().isEmpty()) {
                    Predicate labelName = criteriaBuilder.like(root.get("title").as(String.class), "%" + problem.getTitle() + "%");
                    predicateList.add(labelName);
                }
                Predicate[] and = new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(and));
            }
        };
        return specification;
    }
}
