package com.yaqiwe.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 16:25
 * @Version 1.0
 * 数据库标签表
 */
@Entity
@Table(name = "label")
@Data
@DynamicUpdate
public class Label implements Serializable {

    @Id
    private String id;      /*标签Id*/

    /*标签名称*/
    private String labelName;

    /*状态*/
    private String state;

    /*使用数量*/
    private Long count;

    /*是否推荐*/
    private String recommend;

    /*粉丝数*/
    private Long fans;
}
