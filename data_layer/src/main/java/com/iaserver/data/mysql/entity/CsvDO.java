package com.iaserver.data.mysql.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体类，
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:26
 */

//这里的参数待定，很重要
@Entity
@Table(name = "csv")
public class CsvDO implements Serializable {
    private static final long serialVersionUID = 659538418080127758L;

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
