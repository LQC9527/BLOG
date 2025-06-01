package com.jfcc.jianfei.config;

import com.jfcc.jianfei.filter.JwtFilter;
import com.jfcc.jianfei.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<TestFilter> TestFilter() {
//        FilterRegistrationBean<TestFilter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new TestFilter());
//        bean.addUrlPatterns("/*");
//        bean.setOrder(2);
//        return bean;
//    }


    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new JwtFilter());
        bean.addUrlPatterns("/*");
//        bean.setOrder(1);
        return bean;
    }
}
