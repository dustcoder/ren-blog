package com.ren.blog.service;/*
 *@Author:WuRen
 *@Description:
 *@date: 19:42 2018/12/15
 */

import com.github.pagehelper.PageInfo;
import com.ren.blog.model.Article;

import java.util.List;

public interface ArticleService {

    Integer addArticle(Article article);

    void updateArticle(Article article);

    PageInfo getArticleList(Article article, Integer pageNum, Integer pageSize);

    Article getArticle(Integer articleId);

}
