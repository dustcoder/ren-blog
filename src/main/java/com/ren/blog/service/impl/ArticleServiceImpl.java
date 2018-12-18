package com.ren.blog.service.impl;/*
 *@Author:WuRen
 *@Description:
 *@date: 19:43 2018/12/15
 */

import com.ren.blog.dao.ArticleMapper;
import com.ren.blog.model.Article;
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
    public List<Article> getArticleList() {
        return articleMapper.getArticleList();
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
