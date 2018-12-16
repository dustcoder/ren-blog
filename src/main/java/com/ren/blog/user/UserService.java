package com.ren.blog.user;/*
 *@Author:WuRen
 *@Description:
 *@date: 13:07 2018/10/2
 */

import com.github.pagehelper.PageInfo;
import com.ren.blog.model.UserDomain;

public interface UserService {
    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
