package com.ren.blog.model;/*
 *@Author:WuRen
 *@Description:
 *@date: 11:58 2018/10/2
 */

public class UserDomain {
    private Integer userId;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String userName;

    private String password;

    private String phone;

    public UserDomain() {
    }
}
