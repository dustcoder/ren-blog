package com.ren.blog.service.impl;/*
 *@Author:WuRen
 *@Description:
 *@date: 15:29 2018/12/12
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ren.blog.dao.TagArticleMapper;
import com.ren.blog.dao.TagMapper;
import com.ren.blog.model.Cata;
import com.ren.blog.model.Tag;
import com.ren.blog.model.TagArticle;
import com.ren.blog.service.TagService;


import com.ren.blog.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService{


    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag getTagById(String tagId) {
        return tagMapper.selectByPrimaryKey(Integer.parseInt(tagId));
    }

    @Override
    public PageInfo getTagList(Tag tag, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> list = tagMapper.getTagList(tag);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Tag>(list);
        return pageInfo;
    }

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Override
    public void addTag(Tag tag){
        tagMapper.insert(tag);
    }

    @Override
    public void addOrUpdateTag(Tag tag) {
        tagMapper.addOrUpdateTag(tag);
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
    public List<Map<String,Object>> getTags() throws Exception {
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
