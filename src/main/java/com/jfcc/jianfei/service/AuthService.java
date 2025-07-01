package com.jfcc.jianfei.service;

import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.utils.ResponseEntity;

public interface AuthService {
    ResponseEntity<User> login(User user);

    ResponseEntity<User> signUp(User user);

}
