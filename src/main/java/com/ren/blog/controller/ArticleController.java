package com.ren.blog.controller;/*
 *@Author:WuRen
 *@Description:
 *@date: 21:26 2018/11/18
 */

import com.ren.blog.model.Article;
import com.ren.blog.service.ArticleService;
import com.ren.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/article")
    public ModelAndView article(){
        ModelAndView model = new ModelAndView();
        model.setViewName("article.html");
        return model;
    }

    //新增文章内容
    @PostMapping("/article/add")
    public void summit(HttpServletRequest request, HttpServletResponse response){
        //todo  存库
        String html = request.getParameter("text");
        System.out.println(">>>>>>>>>>" + html);

    }


    @GetMapping("/article/index")
    public ModelAndView createArticle(){
        ModelAndView model = new ModelAndView();
        model.setViewName("article_create.html");
        return model;
    }

    //新增编辑文章信息
//    @PostMapping("/article/saveOrUpdateArticle")
//    public ModelAndView saveOrUpdateArticle(){
//        try {
////            if(article.getArticleId() == null){
////                //新建
////                articleService.addArticle(article);
////            }else{
////                //编辑
////                articleService.updateArticle(article);
////            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //更新成功跳转到文章列表页面
//        ModelAndView model = new ModelAndView();
//        model.setViewName(".html");
//        return model;
//    }



    //新增文章内容
    @PostMapping("/article/saveOrUpdateArticle")
    @Transactional
    public ModelAndView saveOrUpdateArticle(HttpServletRequest request, HttpServletResponse response,
                                            Article article, @RequestParam(value="tagIds") String tagIds){
        try {
            if(article.getArticleId() == null){
                //新建
                articleService.addArticle(article);
            }else{
                //编辑
                articleService.updateArticle(article);
            }
            //删除tag与article关系
            tagService.deleteTagAtticle(article.getArticleId());
            //新增tag 与article
            tagService.insertTagArticle(article.getArticleId(),tagIds);
        }catch (Exception e){
            e.printStackTrace();
        }
        //更新成功跳转到文章列表页面
        ModelAndView model = new ModelAndView();
        model.setViewName("index.html");
        return model;
    }
}
