package com.jfcc.jianfei.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        filterChain.doFilter(request, response);
        // 白名单示例
//        if (path.startsWith("/login") || path.startsWith("/register")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        if (path.startsWith("/login") || path.startsWith("/signUp")) {
////            System.out.println("login登录");
//            filterChain.doFilter(request, response);
//            return;
//        }
    }

    @Override
    public void destroy() {
        System.out.println("注销");
        Filter.super.destroy();
    }
}
