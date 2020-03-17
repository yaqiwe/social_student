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
 * @Date 2020/3/16 20:13
 * @Version 1.0
 * 招聘信息表
 */
@Entity
@Data
@Table(name = "recruit")
@DynamicUpdate
@DynamicInsert
public class Recruit implements Serializable {
    @Id
    /*招聘信息Id*/
    private String id;

    /*招聘职位*/
    private String jobName;
    /*薪资范围*/
    private String salary;
    /*经验要求*/
    private String conditions;
    /*学历要求*/
    private String education;
    /*任职方式*/
    private String type;
    /*办公地址*/
    private String address;
    /*企业ID*/
    private String eid;
    /*发布日期*/
    private Timestamp createTime;
    /*状态,0:关闭 1:开启 2:推荐*/
    private String state;
    /*原网址*/
    private String url;
    /*标签*/
    private String label;
    /*职位描述*/
    private String content1;
    /*职位要求*/
    private String content2;

}
