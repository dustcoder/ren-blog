package com.ren.blog.service.impl;/*
 *@Author:WuRen
 *@Description:
 *@date: 19:43 2018/12/15
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ren.blog.dao.ArticleMapper;
import com.ren.blog.model.Article;
import com.ren.blog.model.Cata;
import com.ren.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Override
    public Article getArticle(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public PageInfo getArticleList(Article article,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.getArticleList(article);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Article>(list);
        return pageInfo;
    }

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Integer addArticle(Article article) {
        return articleMapper.insert(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKey(article);
    }
}
