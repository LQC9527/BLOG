package com.jfcc.jianfei.controller;

import com.jfcc.jianfei.entity.Article;
import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.service.ArticleService;
import com.jfcc.jianfei.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/save")
    public ResponseEntity<Article> save (@RequestBody Article article){
        return articleService.save(article);
    }

    @PostMapping("/create")
    public ResponseEntity<Article> create (@RequestBody User user){
        System.out.println("到了");
        return articleService.create(user);
    }

    @PostMapping("/release")
    public ResponseEntity<Article> release (@RequestBody Article article){
        return articleService.release(article);
    }

    @PostMapping("/get")
    public ResponseEntity<List<Article>> get (@RequestBody User user){
        return articleService.get(user);
    }
}
