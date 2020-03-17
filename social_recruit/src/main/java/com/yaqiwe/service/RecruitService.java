package com.yaqiwe.service;

import com.yaqiwe.entity.Recruit;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/17 9:59
 * @Version 1.0
 * 职位相关操作
 */
public interface RecruitService {
    /**
     * 添加招聘职位
     * @param recruit
     */
    void save(Recruit recruit);

    /**
     * 查询所有招聘职位
     * @return
     */
    List<Recruit> findAll();

    /**
     * 根据Id查询招聘职位
     * @param id
     * @return
     */
    Recruit findById(String id);

    /**
     * 更新职位招聘信息
     * @param recruit
     */
    void update(Recruit recruit);

    /**
     * 删除招聘信息
     * @param recruitId
     */
    void deleteById(String recruitId);

    /**
     * 根据条件查询招聘信息列表
     * @param recruit
     * @return
     */
    List<Recruit> findSearch(Recruit recruit);

    /**
     * 根据条件分页查询招聘信息列表
     * @param recruit
     * @param page
     * @param size
     * @return
     */
    Page<Recruit> pageQuery(Recruit recruit, int page, int size);

    /**
     * 推荐职位
     * @return
     */
    List<Recruit> recommend();

    /**
     * 最新职位
     * @return
     */
    List<Recruit> newList();
}
