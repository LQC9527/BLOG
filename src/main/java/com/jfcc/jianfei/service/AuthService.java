package com.jfcc.jianfei.service;

import com.jfcc.jianfei.entity.User;

public interface AuthService {
    String login(User user);

    String signUp(User user);
}
