package com.ren.blog.service.impl;/*
 *@Author:WuRen
 *@Description:
 *@date: 16:06 2018/12/15
 */

import com.ren.blog.dao.CataMapper;
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
    public List<Map<String, Object>> getCataList(String cataLevel) {
        return cataMapper.getCataList(cataLevel);
    }
}
