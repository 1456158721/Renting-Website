package com.qwq.entity;

import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户类
 * @Author: QWQ
 * @Date: 2019.11.01 14:35:00
 * @Version: 1.0
 */
@Entity
@Table(name = "T_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_USER")
    @SequenceGenerator( name = "SEQ_USER",sequenceName = "SEQ_USERS_ID")
    private Integer userId;
    @Column(name = "userName",length = 50)
    private String userName;
    @Column(name = "userPwd",length = 50)
    @JSONField(serialize=false)
    private String userPwd;
    @Column(name = "userTel",length = 15)
    private Integer userTel;
    @Column(name = "userRealName",length = 20)
    private String userRealName;
    @Column(name = "userIsAdmin",length = 5)
    private String userIsAdmin;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JSONField(serialize=false)
    private Set<House> houses = new HashSet<>();

    public User(){
        
    }

    public User( Integer userId ) {
        this.userId = userId;
    }

    public User( String userName,String userPwd ) {
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public User( String userName,String userPwd,Integer userTel,String userRealName){
        this.userName = userName;
        this.userPwd = userPwd;
        this.userTel = userTel;
        this.userRealName = userRealName;
    }

    public User(String userName,String userPwd,Integer userTel,String userRealName,String userIsAdmin){
        this.userName = userName;
        this.userPwd = userPwd;
        this.userTel = userTel;
        this.userRealName = userRealName;
    }

    public User( Integer userId,String userName,Integer userTel,String userRealName,String userIsAdmin){
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.userRealName = userRealName;
        this.userIsAdmin = userIsAdmin;
    }

    public User( Integer userId,String userName,String userPwd,Integer userTel,String userRealName,String userIsAdmin ) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userTel = userTel;
        this.userRealName = userRealName;
        this.userIsAdmin = userIsAdmin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd( String userPwd ) {
        this.userPwd = userPwd;
    }

    public Integer getUserTel() {
        return userTel;
    }

    public void setUserTel( Integer userTel ) {
        this.userTel = userTel;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName( String userRealName ) {
        this.userRealName = userRealName;
    }

    public String getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin( String userIsAdmin ) {
        this.userIsAdmin = userIsAdmin;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses( Set<House> houses ) {
        this.houses = houses;
    }
}
