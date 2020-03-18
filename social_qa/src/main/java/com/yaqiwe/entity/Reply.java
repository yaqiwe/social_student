package com.yaqiwe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 13:30
 * @Version 1.0
 * 回复表
 */
@Data
@Entity
@Table(name = "reply")
public class Reply implements Serializable {
    @Id
    private String id;

    /*问题ID*/
    private String problemId;
    /*回复的内容*/
    private String content;
    /*回复时间*/
    private Timestamp createTime;
    /*更新时间*/
    private Timestamp updateTime;
    /*回复的人的ID*/
    private String userId;
    /*回复人的昵称*/
    private String nickName;


}
