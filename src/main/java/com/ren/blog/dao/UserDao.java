package com.ren.blog.dao;/*
 *@Author:WuRen
 *@Description:
 *@date: 12:02 2018/10/2
 */

import com.ren.blog.model.UserDomain;

import java.util.List;

public interface  UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
