package com.yaqiwe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 11:16
 * @Version 1.0
 */
@Data
@Table(name = "admin")
@Entity
public class Admin implements Serializable {
    @Id
    private String id;

    /*登录名称*/
    private String loginname;

    /*密码*/
    private String password;

    /*状态*/
    private String state;
}
