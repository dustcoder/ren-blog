package com.ren.blog.controller;/*
 *@Author:WuRen
 *@Description:
 *@date: 14:21 2018/12/1
 */

import com.ren.blog.service.CataService;
import com.ren.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CataController {

    @Autowired
    private CataService cataService;
    @GetMapping("/cata/index")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("cata.html");
        return model;
    }

//
    //分类查询接口  todo  cataLevel
    @GetMapping("/cata/getCataList")
    @ResponseBody
    public Object getCataList( @RequestParam(value="cataLevel") String cataLevel){
        try{
            return ResultUtil.success(cataService.getCataList(cataLevel));
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.fail(e.getMessage());
        }
    }

}
