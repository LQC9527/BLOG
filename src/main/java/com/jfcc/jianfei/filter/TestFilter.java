package com.jfcc.jianfei.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TestFilter注册");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TestFilter调用");

    }

    @Override
    public void destroy() {
        System.out.println("TestFilter注销");
        Filter.super.destroy();
    }
}
