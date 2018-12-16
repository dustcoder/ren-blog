package com.ren.blog.controller;/*
 *@Author:WuRen
 *@Description:
 *@date: 18:34 2018/11/25
 */

import com.ren.blog.model.Tag;
import com.ren.blog.service.TagService;
import com.ren.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag/index")
    public ModelAndView article(){
        ModelAndView model = new ModelAndView();
        model.setViewName("tag.html");
        return model;
    }

    //新增编辑tag接口
    @PostMapping("/tag/addOrUpdateTag")
    public ModelAndView addOrUpdateTag(Tag tag){
        tagService.addOrUpdateTag(tag);
        ModelAndView model = new ModelAndView();
        model.setViewName("tag.html");
        return model;
    }


    //tag查询接口
    @GetMapping("/tag/getTagList")
    @ResponseBody
    public Object getTagList(){
        try{
            return ResultUtil.success(tagService.getTags());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail(e.getMessage());
        }
    }




}
