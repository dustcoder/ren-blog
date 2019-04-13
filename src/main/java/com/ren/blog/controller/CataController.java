package com.ren.blog.controller;
/*
 *@Author:WuRen
 *@Description:
 *@date: 14:21 2018/12/1
 */

import com.github.pagehelper.PageInfo;
import com.ren.blog.model.Cata;
import com.ren.blog.service.CataService;
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
public class CataController {

    @Autowired
    private CataService cataService;
    @GetMapping("/cata/index")
    public ModelAndView index(Cata cata,@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        ModelAndView model = new ModelAndView();
        model.setViewName("cata.html");
        PageInfo pageInfo = cataService.getCataList(cata,pageNum,pageSize);
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
        model.addObject("cataName",cata.getCataName());
        model.addObject("enable",cata.getEnable());
//        model.addObject("cataList",);
        return model;
    }

    @PostMapping("/cata/saveOrUpdateCata")
    @ResponseBody
    public Object saveOrUpdateCata(HttpServletRequest request,Cata cata){
        try {
            cataService.saveOrUpdateCata(cata);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail(e.getMessage());
        }
        return ResultUtil.success("");
    }


    @GetMapping("/cata/getCataById")
    @ResponseBody
    public  Object getCataById(HttpServletRequest request){
        String cataId = request.getParameter("cataId");
        return ResultUtil.success(cataService.getCataById(cataId));
    }


    @GetMapping("/cata/getAllCataList")
    @ResponseBody
    public Object getAllCataList(HttpServletRequest request){
        return ResultUtil.success(cataService.getAllCataList());
    }

}
