package com.yaqiwe.service;

import com.yaqiwe.entity.Problem;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 14:24
 * @Version 1.0
 * 问题服务操作
 */
public interface ProblemService {
    /**
     * 发布问题
     * @param problem
     */
    void save(Problem problem);

    /**
     * 查询所有问题
     * @return
     */
    List<Problem> findAll();

    /**
     * 根据Id查找问题
     * @param problemId
     * @return
     */
    Problem findById(String problemId);

    /**
     * 更新问题信息
     * @param problem
     */
    void update(Problem problem);

    /**
     * 删除问题
     * @param problemId
     */
    void deleteById(String problemId);

    /**
     * 根据条件查询问题
     * @param problem
     * @return
     */
    List<Problem> findSearch(Problem problem);

    /**
     * 根据条件分页查询问题
     * @param problem
     * @param page
     * @param size
     * @return
     */
    Page<Problem> pageQuery(Problem problem, int page, int size);

    /**
     * 最新问题列表
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    Page<Problem> newList(String labelId, int page, int size);

    /**
     * 热门回答列表
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    Page<Problem> hotList(String labelId, int page, int size);

    /**
     * 等待回答列表
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    Page<Problem> witList(String labelId, int page, int size);
}
