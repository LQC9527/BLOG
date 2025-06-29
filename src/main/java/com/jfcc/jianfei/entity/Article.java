package com.jfcc.jianfei.entity;

import lombok.Data;

import java.util.List;

@Data
public class Article {

    private String articleId;

    private String userNo;

    private String title;

    private List<String> picPath;

    private String content;

    private String status;

    private String txTime;

    private String upTime;
}
