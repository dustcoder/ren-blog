package com.ren.blog.service;/*
 *@Author:WuRen
 *@Description:
 *@date: 15:56 2018/12/15
 */

import com.github.pagehelper.PageInfo;
import com.ren.blog.model.Cata;

import java.util.List;
import java.util.Map;

public interface CataService {

    PageInfo getCataList(Cata cata,Integer pageNum,Integer pageSize);

    void saveOrUpdateCata(Cata cata);

    Cata getCataById(String cataId);


}
