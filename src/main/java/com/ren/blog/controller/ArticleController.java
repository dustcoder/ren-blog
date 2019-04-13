package com.ren.blog.controller;
/*
 *@Author:WuRen
 *@Description:
 *@date: 21:26 2018/11/18
 */

import com.ren.blog.model.Article;
import com.ren.blog.service.ArticleService;
import com.ren.blog.service.TagService;
import com.ren.blog.util.ResultUtil;
import com.ren.blog.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;


    @Value("${img.location}")
    private String ROOT;

    //文章内容编辑页面
    @GetMapping("/article")
    public ModelAndView article(@RequestParam(value="articleId") Integer articleId){
        ModelAndView model = new ModelAndView();
        model.addObject("articleId",articleId);
        model.addObject("content",articleService.getArticle(articleId).getContent());
        model.addObject("mdContent",articleService.getArticle(articleId).getMdContent());
        model.setViewName("article.html");
        return model;
    }

    //新增文章内容
    @PostMapping("/article/add")
    @ResponseBody
    public String summit(Article article){
        try {
            articleService.updateArticle(article);
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return  "true";
    }



    //文章信息编辑页面
    @GetMapping("/article/index")
    public ModelAndView createArticle(@RequestParam(value="articleId",required=false ) Integer articleId){
        //Article article = articleService.getArticle(articleId);
        ModelAndView model = new ModelAndView();
        model.setViewName("article_create.html");
        //model.addObject("article",article);
        model.addObject("articleId",articleId);
        return model;
    }

    //新增文章信息
    @PostMapping("/article/saveOrUpdateArticle")
    @Transactional
    public ModelAndView saveOrUpdateArticle(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value="tagIds") String tagIds,
                                            @RequestParam(value = "file") MultipartFile attach){
        try {
            String fileName = attach.getOriginalFilename();
            int pot = fileName.lastIndexOf(".");
            String exName = "";
            if(pot != -1){
                exName = fileName.substring(pot);
            }
            String newName = UuidUtils.getUUID() + exName;
            Files.copy(attach.getInputStream(), Paths.get(ROOT, newName));
            Article article = new Article();
            article.setArticleName(request.getParameter("articleName"));
            article.setFileName(newName);
            System.out.println(newName); //文件名
            article.setEnable(request.getParameter("enable"));
            article.setCataId(Integer.parseInt(request.getParameter("cataId")));
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
        ModelAndView view = new ModelAndView("redirect:/index");
        return view;
    }
}
