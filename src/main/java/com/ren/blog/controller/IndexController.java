package com.ren.blog.controller;
/*
 *@Author:WuRen
 *@Description:
 *@date: 19:38 2018/12/1
 */

import com.github.pagehelper.PageInfo;
import com.ren.blog.model.Article;
import com.ren.blog.model.Cata;
import com.ren.blog.model.TagArticle;
import com.ren.blog.service.ArticleService;
import com.ren.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public ModelAndView index(Article article, @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize){

        ModelAndView model = new ModelAndView();
        model.setViewName("index.html");
        try {
            PageInfo pageInfo = articleService.getArticleList(article,pageNum,pageSize);
            model.addObject("pageNum", pageInfo.getPageNum());
            //获得一页显示的条数
            model.addObject("pageSize", pageInfo.getPageSize());
            //是否是第一页
            model.addObject("isFirstPage", pageInfo.isIsFirstPage());
            //获得总页数
            model.addObject("totalPages", pageInfo.getPages());
            //是否是最后一页
            model.addObject("isLastPage", pageInfo.isIsLastPage());
            model.addObject("pageInfo", pageInfo);
            //分类名
            model.addObject("article",article);
//            model.addObject("enable",article.getEnable());

//            model
//        model.addObject("cataList",);
            return model;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }







//        ModelAndView view = new ModelAndView();
//        view.setViewName("index.html");
//        try{
//            //主页查找文章信息
//            List<Article> articleList = articleService.getArticleList(article,pageNum,pageSize);
//            //批量查询 一对多有什么好方法 todo
//            for(Article article : articleList){
//                List<TagArticle> tagArticleList =  tagService.getTagArticleList(article.getArticleId());
//                String tagArticleName = "";
//                for(TagArticle tagArticle : tagArticleList){
//                    tagArticleName = tagArticleName + " " +  tagArticle.getTagName() ;
//                }
//                article.setTagName(tagArticleName);
//                if(article.getCataId() != null && article.getSubCataId() != null){
//                    article.setCataName(article.getFirstCateName() + "-" + article.getSecCataName());
//                }else {
//                    article.setCataName(article.getFirstCateName() == null ? article.getSecCataName() : article.getFirstCateName());
//                }
//            }
//            view.addObject("articleList",articleList);
//        }catch (Exception e){
//            e.printStackTrace();
//            view.addObject("articleList",null);
//        }
//        return view;
    }
}
