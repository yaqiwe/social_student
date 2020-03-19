package com.yaqiwe.service.impl;

import com.yaqiwe.entity.Article;
import com.yaqiwe.repository.ArticleRepository;
import com.yaqiwe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.SnowflakeUtil;

import java.util.List;
import java.util.Optional;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 10:19
 * @Version 1.0
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleR;

    @Autowired
    SnowflakeUtil snowflakeU;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void save(Article article) {
        article.setId(snowflakeU.nextId()+"");
        article.setIsTop("0");
        article.setState("0");
        article.setVisits(0L);
        article.setThumbUp(0L);
        article.setComment(0L);
        articleR.save(article);
    }

    @Override
    public List<Article> findAll() {
        return articleR.findAll();
    }

    @Override
    public Article findById(String articleId) {
        Article article=(Article) redisTemplate.opsForValue().get("article_"+articleId);
        if(article==null){
            Optional<Article> byId = articleR.findById(articleId);
            if(byId.isPresent()){
                article=byId.get();
                redisTemplate.opsForValue().set("article_"+articleId,article);
            }
        }
        return article;
    }

    @Override
    public void updateState(String articleId) {
        articleR.updateState(articleId);
    }

    @Override
    public void addThumbUp(String articleId) {
        articleR.addThumbUp(articleId);
    }

    @Override
    public List<Article> findByIsTop() {
        return articleR.findByIsTop("1");
    }

    @Override
    public Page<Article> findByChannelId(Long channelId, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return articleR.findByChannelId(channelId,pageable);
    }

    @Override
    public Page<Article> findByColumnlId(String columnlId, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return articleR.findByColumnId(columnlId,pageable);
    }

    @Override
    public void deleteById(String articleId) {
        articleR.deleteById(articleId);
        redisTemplate.delete("article_"+articleId);
    }
}
