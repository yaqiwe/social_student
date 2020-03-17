package com.yaqiwe.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 20:08
 * @Version 1.0
 * 企业表
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "enterprise")
public class Enterprise implements Serializable {

    @Id
    /*企业ID*/
    private String id;

    /*企业名称*/
    private String name;
    /*企业简介*/
    private String summary;
    /*企业地址*/
    private String address;
    /*标签列表,用逗号,分隔*/
    private String labels;
    /*企业位置坐标,经度,纬度*/
    private String coordinate;
    /*是否热门0表示非热门1表示热门*/
    private String ishot;
    /*LOGO*/
    private String logo;
    /*职位数*/
    private long jobcount;
    /*url*/
    private String url;
}
