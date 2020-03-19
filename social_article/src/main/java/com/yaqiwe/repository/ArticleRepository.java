package com.yaqiwe.repository;

import com.yaqiwe.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 10:06
 * @Version 1.0
 */
public interface ArticleRepository extends JpaRepository<Article,String> , JpaSpecificationExecutor<Article> {

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE article SET state =1 WHERE id= ?")
    public void updateState(String articleId);

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE article SET thumb_up=thumb_up+1 WHERE id=?")
    public void addThumbUp(String articleId);

    List<Article> findByIsTop(String isTop);

    Page<Article> findByChannelId(Long channelId, Pageable pageable);

    Page<Article> findByColumnId(String columnId, Pageable pageable);
}
