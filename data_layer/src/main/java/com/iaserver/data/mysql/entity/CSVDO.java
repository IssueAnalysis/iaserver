package com.iaserver.data.mysql.entity;

import com.iaserver.data.mongdb.CSVitem;
import org.bson.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 存放在mysql中得csv单项
 * User: 钟镇鸿
 * Date: 2020/4/7
 * Time: 13:13
 * Description:
 */
@Entity
@Table(name="csv")
public class CSVDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "url")
    private String url;

    @Basic
    @Column(name = "user_id", insertable = false, updatable = false)
    private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserDO userByUserId;

    public CSVDO(){
    }
    public CSVDO(long id, String url, long userId, UserDO userByUserId) {
        this.id = id;
        this.url = url;
        this.userId = userId;
        this.userByUserId = userByUserId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserDO getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserDO userByUserId) {
        this.userByUserId = userByUserId;
    }
}
