package com.sgw.provider.entity;

import java.io.Serializable;

/**
 * (UserInfo)实体类
 *
 * @author makejava
 * @since 2022-09-05 15:02:29
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -90339781519951532L;
    
    private Integer userId;
    
    private String userName;
    
    private Integer age;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}

