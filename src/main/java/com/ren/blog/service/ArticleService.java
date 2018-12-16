package com.ren.blog.service;/*
 *@Author:WuRen
 *@Description:
 *@date: 19:42 2018/12/15
 */

import com.ren.blog.model.Article;

import java.util.List;

public interface ArticleService {

    Integer addArticle(Article article);

    void updateArticle(Article article);

    List<Article> getArticleList();

}
