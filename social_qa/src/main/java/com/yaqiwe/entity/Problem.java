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
 * @Date 2020/3/18 13:20
 * @Version 1.0
 * 问题表
 */
@Entity
@Data
@Table(name = "problem")
@DynamicInsert
@DynamicUpdate
public class Problem implements Serializable {
    @Id
    private String id;

    /*问题标题*/
    private String title;
    /*问题内容*/
    private String content;
    /*发布日期*/
    private Timestamp createTime;
    /*更新日期*/
    private Timestamp updateTime;
    /*问题发布人ID*/
    private String userId;
    /*发布人昵称*/
    private String nickName;
    /*浏览量*/
    private Long visits;
    /*点赞数*/
    private Long thumbUp;
    /*回复数*/
    private Long reply;
    /*是否解决*/
    private String solve;
    /*最新回复人*/
    private String replyName;
    /*最新回复时间*/
    private Timestamp replyTime;

}
