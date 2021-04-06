package com.zdk.filter;

import com.jfinal.core.JFinalFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zdk
 * @date 2021/4/3 15:25
 */
public class MyFilter extends JFinalFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String username= (String) request.getSession().getAttribute("username");
        System.out.println("过滤时username的值为："+username);
        if(username==null){
            response.sendRedirect("errorPage.html");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
