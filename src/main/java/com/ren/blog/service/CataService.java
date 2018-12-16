package com.ren.blog.service;/*
 *@Author:WuRen
 *@Description:
 *@date: 15:56 2018/12/15
 */

import java.util.List;
import java.util.Map;

public interface CataService {

    public List<Map<String,Object>> getCataList(String cataLevel);
}
