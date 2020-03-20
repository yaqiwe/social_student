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
 * @Date 2020/3/19 16:55
 * @Version 1.0
 */
@Entity
@Table(name = "user")
@Data
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    @Id
    private String id;

    /*手机号码*/
    private String mobile;
    /*密码*/
    private String password;
    /*昵称*/
    private String nickname;
    /*性别*/
    private String sex;
    /*出生年月日*/
    private Timestamp birthday;
    /*头像*/
    private String avatar;
    /*E-Mail*/
    private String email;
    /*注册日期*/
    private Timestamp regdate;
    /*修改日期*/
    private Timestamp updatedate;
    /*最后登陆日期*/
    private Timestamp lastdate;
    /*在线时长（分钟）*/
    private Long online;
    /*兴趣*/
    private String interest;
    /*个性*/
    private String personality;
    /*粉丝数*/
    private Integer fanscount;
    /*关注数*/
    private Integer followcount;

}
