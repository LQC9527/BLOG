package com.jfcc.jianfei.service.impl;

import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.service.AuthService;
import com.jfcc.jianfei.utils.EDcryptUtils;
import com.jfcc.jianfei.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private EDcryptUtils utils;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String login(User user) {
        String password = redisTemplate.opsForValue().get(user.getUserNo());
        System.out.println(password);
        if (!StringUtils.isBlank(password)) {
            if (utils.JBcryptMatched( user.getPassword(),password)) {
                return jwtUtils.generateToken(user.getUserNo());
            } else {
                return "密码错误";
            }
        } else {
            return "账号不存在";
        }
    }

    @Override
    public String signUp(User user) {
        String password = redisTemplate.opsForValue().get(user.getUserNo());
        if (StringUtils.isBlank(password)) {
            redisTemplate.opsForValue().set(user.getUserNo(),utils.JBcryptEndode(user.getPassword()));
            return jwtUtils.generateToken(user.getUserNo());
        } else {
            return "此用户号已存在";
        }
    }

}
