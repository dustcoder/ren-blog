package com.ren.blog.controller;
/*
 *@Author:WuRen
 *@Description:
 *@date: 13:10 2018/10/2
 */


import com.ren.blog.model.UserDomain;
import com.ren.blog.user.UserService;
import com.ren.blog.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {




    @Autowired
    private UserService userService;

    @Value("${web.path}")
    private String PATH;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }



    @GetMapping("/back")
    public ModelAndView showLogin(){
        try{
            ModelAndView view = new ModelAndView();
            //生成验证码 todo
            view.setViewName("login.html");
            view.addObject("PATH",PATH);
//            view.addObject("PATH",);
            return view;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request,HttpServletResponse response){
        try{
            //校验验证码
            String checkCode = request.getParameter("checkCode");
            String random = (String)request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
            if(!checkCode.equals(random)){
                return null;
            }
            String userName = request.getParameter("userName");
            String pwd = request.getParameter("pwd");

//            String smi=convertMD5(pwd);

            if("wuren".equals(userName) && "c154b3c615c452f74494987653cd6d30".equals(pwd)){
                request.getSession().setAttribute("userName",userName);
                request.getSession().setAttribute("pwd",pwd);
                return  "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    //todo 退出系统
    @GetMapping("/loginOut")
    public ModelAndView loginOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session  = request.getSession();
        session.invalidate();
        ModelAndView view = new ModelAndView();
        view.setViewName("login.html");
        return view;
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }


}
