package com.ren.blog.util;/*
 *@Author:WuRen
 *@Description:
 *@date: 22:42 2018/12/30
 */

import java.util.UUID;

public class UuidUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
