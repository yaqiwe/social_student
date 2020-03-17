package com.yaqiwe.service;

import com.yaqiwe.entity.Enterprise;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 21:18
 * @Version 1.0
 * 企业相关操作
 */
public interface EnterpriseService {

    /**
     * 增加企业
     * @param enterprise
     */
    void save(Enterprise enterprise);

    /**
     * 查询全部企业列表
     * @return
     */
    List<Enterprise> findAll();

    /**
     * 根据Id查询企业
     * @param Id
     * @return
     */
    Enterprise findById(String Id);

    /**
     * 更新企业信息
     * @param enterprise
     */
    void update(Enterprise enterprise);

    /**
     * 根据id删除企业
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据条件查询企业
     * @param enterprise
     * @return
     */
    List<Enterprise> findSearch(Enterprise enterprise);

    /**
     * 查询热门企业列表
     * @return
     */
    List<Enterprise> hotList();

    /**
     * 根据条件分页查询企业列表
     * @param enterprise
     * @param page
     * @param size
     * @return
     */
    Page<Enterprise> pageQuery(Enterprise enterprise, int page, int size);

}
