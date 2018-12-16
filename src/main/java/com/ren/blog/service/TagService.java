package com.ren.blog.service;/*
 *@Author:WuRen
 *@Description:
 *@date: 15:28 2018/12/12
 */

import com.ren.blog.model.Article;
import com.ren.blog.model.Tag;
import com.ren.blog.model.TagArticle;

import java.util.List;
import java.util.Map;

public interface TagService {

    public void addTag(Tag tag) throws Exception;

    public List<Map<String,Object>> getTags() throws Exception;

    public void addOrUpdateTag(Tag tag);

    void deleteTagAtticle(Integer article);

    void insertTagArticle(Integer articleId,String tagIds);

    List<TagArticle> getTagArticleList(Integer articleId);

}
