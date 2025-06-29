package com.jfcc.jianfei.service;

import com.jfcc.jianfei.entity.Article;
import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.utils.ResponseEntity;

import java.util.List;


public interface ArticleService {

    ResponseEntity<Article> create(User user);

    ResponseEntity<Article> save(Article article);

    ResponseEntity<List<Article>> get(User user);

    ResponseEntity<Article> release(Article article);
}
