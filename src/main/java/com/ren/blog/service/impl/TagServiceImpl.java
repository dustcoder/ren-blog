package com.ren.blog.service.impl;/*
 *@Author:WuRen
 *@Description:
 *@date: 15:29 2018/12/12
 */

import com.ren.blog.dao.TagArticleMapper;
import com.ren.blog.dao.TagMapper;
import com.ren.blog.model.Article;
import com.ren.blog.model.Tag;
import com.ren.blog.model.TagArticle;
import com.ren.blog.service.TagService;


import com.ren.blog.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService{


    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Override
    public void addTag(Tag tag){
        tagMapper.insert(tag);
    }

    @Override
    public void addOrUpdateTag(Tag tag) {
        tag.setCreateTime(new Date());
        if(tag.getTagId() == null){
            //插如
            tagMapper.insert(tag);
        }else{
            //
            tagMapper.updateByPrimaryKey(tag);
        }
    }

    @Override
    public void insertTagArticle(Integer articleId, String tagIds) {
        String[] tagIdArray = tagIds.split(",");

        for(Integer tagId : ConvertUtil.stringArrayToIntegerArray(tagIdArray)){
            TagArticle tagArticle = new TagArticle();
            tagArticle.setArticleId(articleId);
            tagArticle.setTagId(tagId);
            tagArticleMapper.insert(tagArticle);
        }
    }

    @Override
    public List<Map<String, Object>> getTags() throws Exception {
        return tagMapper.getTags();
    }

    @Override
    public void deleteTagAtticle(Integer articleId) {
        tagArticleMapper.deleteTagArticle(articleId);
    }

    @Override
    public List<TagArticle> getTagArticleList(Integer articleId) {
        return tagArticleMapper.getTagArticleList(articleId);
    }
}
