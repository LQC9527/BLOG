package com.jfcc.jianfei.service.impl;

import com.alibaba.fastjson2.JSON;
import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.service.AuthService;
import com.jfcc.jianfei.utils.EDcryptUtils;
import com.jfcc.jianfei.utils.JwtUtils;
import com.jfcc.jianfei.utils.ResponseEntity;
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
    public ResponseEntity<User> login(User user) {
        String json = redisTemplate.opsForValue().get(user.getUserNo());
        if (!StringUtils.isBlank(json)) {
            User userdata = JSON.parseObject(json, User.class);
            if (utils.JBcryptMatched( user.getPassword(),userdata.getPassword())) {
                String token = jwtUtils.generateToken(user.getUserNo());
                user.setToken(token);
                return ResponseEntity.success(user);
            }
            return ResponseEntity.failure(511,"密码错误");
        }
        return ResponseEntity.failure(510,"账户不存在");
    }

    @Override
    public ResponseEntity<User> signUp(User user) {
        String json = redisTemplate.opsForValue().get(user.getUserNo());
        if (StringUtils.isNotBlank(json)) {
            return ResponseEntity.failure(512,"此用户已存在");
        }
        String encodePsd = utils.JBcryptEndode(user.getPassword());
        user.setPassword(encodePsd);
        user.setRole("100");

        return ResponseEntity.success(user);
    }

    public void redisSave(User user) {

    }


}
