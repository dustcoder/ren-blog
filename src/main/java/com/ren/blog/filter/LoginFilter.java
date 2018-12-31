package com.ren.blog.filter;
/*
 *@Author:WuRen
 *@Description:
 *@date: 14:16 2018/12/12
 */

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    String[] incluedUrls = new String[]{"/user/back","/js/jquery-3.3.1.js","/js/jquery.md5.js",
            "/user/getVerify","/favicon.ico","/user/login"};

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    @Override
    public void destroy() {}


    @Override
    public void doFilter
            (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterchain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        System.out.println(uri);

        boolean needFilter = isNeedFilter(uri);

        if(!needFilter){
            filterchain.doFilter(servletRequest,servletResponse);
        }else{
            if(session != null && session.getAttribute("userName") != null){
                filterchain.doFilter(servletRequest,servletResponse);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                if(requestType != null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    response.sendRedirect(request.getContextPath()+"/user/back");
                }
            }
        }
    }
    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}

    public boolean isNeedFilter(String uri){

        for(String includeUrl : incluedUrls){
            if(includeUrl.equals(uri)){
                return  false;
            }
        }
        return  true;

    }



}
