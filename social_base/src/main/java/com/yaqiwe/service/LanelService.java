package com.yaqiwe.service;

import com.yaqiwe.entity.Label;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 18:07
 * @Version 1.0
 */
public interface LanelService  {

    /**
     * 查询所有结果
     * @return
     */
    public List<Label> findAll();

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Label findById(String id);

    /**
     * 保存数据
     * @param label
     */
    public void save(Label label);

    /**
     * 更新记录
     * @param label
     */
    public void update(Label label);

    /**
     * 数据Id删除记录
     * @param id
     */
    public void deleteById(String id);

    /**
     * 根据条件查询标签
     * @param label
     * @return
     */
    List<Label> findSearch(Label label);

    /**
     * 根据条件分页查询标签
     * @param label
     * @param page
     * @param size
     * @return
     */
    Page<Label> pageQuery(Label label, int page, int size);
}
