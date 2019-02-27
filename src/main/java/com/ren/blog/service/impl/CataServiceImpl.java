package com.ren.blog.service.impl;/*
 *@Author:WuRen
 *@Description:
 *@date: 16:06 2018/12/15
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ren.blog.dao.CataMapper;
import com.ren.blog.model.Cata;
import com.ren.blog.service.CataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CataServiceImpl implements CataService{

    @Autowired
    private CataMapper cataMapper;
    @Override
    public PageInfo getCataList(Cata cata,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cata> list = cataMapper.getCataList(cata);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Cata>(list);
        return pageInfo;
//        return
    }


    @Override
    public void saveOrUpdateCata(Cata cata) {
        cataMapper.saveOrUpdateCata(cata);
    }

    @Override
    public Cata getCataById(String cataId) {
        return cataMapper.selectByPrimaryKey(Integer.parseInt(cataId));
    }
}
