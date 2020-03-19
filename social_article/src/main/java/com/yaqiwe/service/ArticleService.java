package com.yaqiwe.service;

import com.yaqiwe.entity.Article;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 10:13
 * @Version 1.0
 */
public interface ArticleService {
    /**
     * 发布文章
     * @param article
     */
    void save(Article article);

    /**
     * 查询所有文章
     * @return
     */
    List<Article> findAll();

    /**
     * 根据Id查询文章
     * @param articleId
     * @return
     */
    Article findById(String articleId);

    /**
     * 审核文章
     * @param articleId
     */
    void updateState(String articleId);

    /**
     * 文章点赞
     * @param articleId
     */
    void addThumbUp(String articleId);

    /**
     * 头条文章
     * @return
     */
    List<Article> findByIsTop();

    /**
     * 根据频道Id获取文章列表
     * @param channelId
     * @param page
     * @param size
     * @return
     */
    Page<Article> findByChannelId(Long channelId, int page, int size);

    /**
     * 根据专栏ID获取文章列表
    * @param columnlId
     * @param page
     * @param size
     * @return
     */
    Page<Article> findByColumnlId(String columnlId, int page, int size);

    /**
     * 删除文章
     * @param articleId
     */
    void deleteById(String articleId);
}
