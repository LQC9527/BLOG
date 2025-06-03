package com.jfcc.jianfei.service.impl;

import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.service.AuthService;
import com.jfcc.jianfei.utils.EDcryptUtils;
import com.jfcc.jianfei.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

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
        Map<Object, Object> map = redisTemplate.opsForHash().entries(user.getUserNo());
        if (!map.containsKey(user.getUserNo())) {
            this.putUser(user);
            return "注册成功";
        } else {
            return "此用户号已存在";
        }
    }

    public void putUser(User user) {
        redisTemplate.opsForHash().put(user.getUserNo(),"password",user.getPassword());
        redisTemplate.opsForHash().put(user.getUserNo(),"role",user.getRole());
    }


}
