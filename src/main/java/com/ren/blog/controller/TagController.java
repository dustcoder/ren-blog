package com.ren.blog.controller;/*
 *@Author:WuRen
 *@Description:
 *@date: 18:34 2018/11/25
 */

import com.github.pagehelper.PageInfo;
import com.ren.blog.model.Tag;
import com.ren.blog.service.TagService;
import com.ren.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag/index")
    public ModelAndView article(Tag tag, @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize){

        ModelAndView model = new ModelAndView();
        model.setViewName("tag.html");
        PageInfo pageInfo = tagService.getTagList(tag,pageNum,pageSize);
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
        model.addObject("tagName",tag.getTagName());
        model.addObject("enable",tag.getEnable());
//        model.addObject("cataList",);
        return model;
    }

    //新增编辑tag接口
    @PostMapping("/tag/addOrUpdateTag")
    @ResponseBody
    public Object addOrUpdateTag(Tag tag){
        try {
            tagService.addOrUpdateTag(tag);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail(e.getMessage());
        }
        return ResultUtil.success("");
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

    @GetMapping("/tag/getTagById")
    @ResponseBody
    public  Object getCataById(HttpServletRequest request){
        String tagId = request.getParameter("tagId");
        return ResultUtil.success(tagService.getTagById(tagId));
    }




}
