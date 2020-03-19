package com.yaqiwe.controller;

import com.yaqiwe.entity.Article;
import com.yaqiwe.service.ArticleService;
import entity.PageResoult;
import entity.Resoult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.ResoultUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 10:11
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleS;

    @PostMapping
    public Resoult save(@RequestBody Article article) {
        articleS.save(article);
        return ResoultUtil.success("发布文章成功");
    }

    @GetMapping
    public Resoult findAll(){
        return ResoultUtil.success(articleS.findAll());
    }

    @GetMapping("/{articleId}")
    public Resoult findById(@PathVariable String articleId){
        return ResoultUtil.success(articleS.findById(articleId));
    }

    @DeleteMapping("/{articleId}")
    public Resoult deleteById(@PathVariable String articleId){
        articleS.deleteById(articleId);
        return ResoultUtil.success("删除文章成功");
    }

    @PutMapping("/examine/{articleId}")
    public Resoult updateState(@PathVariable String articleId){
        articleS.updateState(articleId);
        return ResoultUtil.success("审核文章成功");
    }

    @PutMapping("/thumbUp/{articleId}")
    public Resoult addThumbUp(@PathVariable String articleId){
        articleS.addThumbUp(articleId);
        return ResoultUtil.success("点赞成功");
    }

    @GetMapping("/top")
    public Resoult getArticleS() {
        return ResoultUtil.success(articleS.findByIsTop());
    }

    @PostMapping("/channel/{channelId}/{page}/{size}")
    public Resoult findByChannelId(@PathVariable Long channelId,@PathVariable int page,@PathVariable int size){
        Page<Article> articlePage=articleS.findByChannelId(channelId,page,size);
        return ResoultUtil.success(new PageResoult(articlePage.getTotalElements(),articlePage.getContent()));
    }

    @PostMapping("/column/{columnlId}/{page}/{size}")
    public Resoult findByColumnlId(@PathVariable String columnlId,@PathVariable int page,@PathVariable int size){
        Page<Article> articlePage=articleS.findByColumnlId(columnlId,page,size);
        return ResoultUtil.success(new PageResoult(articlePage.getTotalElements(),articlePage.getContent()));
    }
}
