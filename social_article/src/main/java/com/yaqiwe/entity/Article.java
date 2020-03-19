package com.yaqiwe.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author yaqiwe
 * @Date 2020/3/19 9:58
 * @Version 1.0
 * 文章表
 */
@Entity
@Table(name = "article")
@Data
@DynamicInsert
@DynamicUpdate
public class Article implements Serializable {

    @Id
    private String id;

    /*专栏ID*/
    private String columnId;
    /*用户ID*/
    private String userId;
    /*文章标题*/
    private String title;
    /*文章内容*/
    private String content;
    /*文章封面*/
    private String iamge;
    /*发布时间*/
    private Timestamp createTime;
    /*更新时间*/
    private Timestamp updateTime;
    /*是否公开0:不公开,1:公开*/
    private String isPublic;
    /*是否置顶0:不置顶,1:置顶*/
    private String isTop;
    /*浏览量*/
    private Long visits;
    /*点赞数*/
    private Long thumbUp;
    /*评论数*/
    private Long comment;
    /*审核状态0:未审核,1:已审核*/
    private String state;
    /*所属频道ID*/
    private Long channelId;
    /*url地址*/
    private String url;
    /*文章类型0:分享,1专栏D*/
    private String type;

}
