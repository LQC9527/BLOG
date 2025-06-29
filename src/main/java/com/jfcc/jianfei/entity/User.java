package com.jfcc.jianfei.entity;

import lombok.Data;

@Data
public class User {

    private String userNo;

    private String password;

    private String role;

    private String token;

    private String txTime;

    private String upTime;
}
