package com.yaqiwe.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author yaqiwe
 * @Date 2020/3/18 15:31
 * @Version 1.0
 * 问题标签关系
 */
@Data
@Entity
@Table(name = "pl")
public class Pl implements Serializable {
    @Id
    private String problemId;

    @Id
    private String labelId;
}
