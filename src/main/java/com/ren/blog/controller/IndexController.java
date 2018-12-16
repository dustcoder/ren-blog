package com.ren.blog.controller;/*
 *@Author:WuRen
 *@Description:
 *@date: 19:38 2018/12/1
 */

import com.ren.blog.model.Article;
import com.ren.blog.model.TagArticle;
import com.ren.blog.service.ArticleService;
import com.ren.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public ModelAndView index(){
        //主页查找文章信息
        List<Article> articleList = articleService.getArticleList();
        //批量查询 一对多有什么好方法 todo
        for(Article article : articleList){
            List<TagArticle> tagArticleList =  tagService.getTagArticleList(article.getArticleId());
            String tagArticleName = "";
            for(TagArticle tagArticle : tagArticleList){
                tagArticleName = tagArticleName + " " +  tagArticle.getTagName() ;
            }
            article.setTagName(tagArticleName);
            if(article.getCataId() != null && article.getSubCataId() != null){
                article.setCataName(article.getFirstCateName() + "-" + article.getSecCataName());
            }else {
                article.setCataName(article.getFirstCateName() == null ? article.getSecCataName() : article.getFirstCateName());
            }
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("index.html");
        view.addObject("articleList",articleList);
        return view;
    }
}
