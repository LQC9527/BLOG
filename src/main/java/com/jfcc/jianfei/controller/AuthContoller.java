package com.jfcc.jianfei.controller;

import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.service.AuthService;
import com.jfcc.jianfei.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthContoller {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return authService.signUp(user);
    }

}
