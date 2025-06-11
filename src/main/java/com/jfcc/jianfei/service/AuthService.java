package com.jfcc.jianfei.service;

import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.utils.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> login(User user);

    ResponseEntity<String> signUp(User user);
}
