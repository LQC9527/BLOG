package com.jfcc.jianfei.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.jfcc.jianfei.entity.Article;
import com.jfcc.jianfei.entity.User;
import com.jfcc.jianfei.enums.ArticleStatus;
import com.jfcc.jianfei.enums.DataName;
import com.jfcc.jianfei.service.ArticleService;
import com.jfcc.jianfei.utils.ConvertUtils;
import com.jfcc.jianfei.utils.ResponseEntity;
import org.apache.commons.lang3.time.DateUtils;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static Map<String, Article> cache = new HashMap<>();

    @Autowired
    private RedissonClient redissonClient;



    @Override
    public ResponseEntity<Article> create(User user) {
        Article article = new Article();
        String articleId = new SnowflakeGenerator().next().toString();
        article.setArticleId(articleId);
        article.setStatus(ArticleStatus.INIT.getStatus());
        article.setUserNo(user.getUserNo());
        article.setTitle("新建文章标题");
        article.setContent("新建文章内容");
        article.setTxTime(DateUtil.now());
        cache.put(articleId,article);
        return ResponseEntity.success(article);
    }

    @Override
    public ResponseEntity<Article> save(Article article) {
        return ResponseEntity.success(cache.replace(article.getArticleId(),article));
    }

    @Override
    public ResponseEntity<List<Article>> get(User user) {
        RList<String> articleIds = redissonClient.getList(user.getUserNo());
        List<Map<String,String>> listValue= getListValue(articleIds.readAll());
        List<Article> articleList = new ArrayList<>();
        for (Map tmp:listValue) {
            articleList.add(BeanUtil.toBean(tmp, Article.class));
        }

        for (Article tmp :articleList) {
            RList<String> picPaths = redissonClient.getList(user.getUserNo());
            tmp.setPicPath(picPaths.readAll());
        }
        return ResponseEntity.success(articleList);
    }

    @Override
    public ResponseEntity<Article> release(Article article) {
        RMap<String,Object> rMap = redissonClient.getMap(DataName.CONTEXT.getName()+":"+article.getArticleId());
        RList<String> rList = redissonClient.getList(article.getArticleId());
        rList.addAll(rList);
        rMap.remove("picPath");
        rMap.putAll(BeanUtil.beanToMap(article));
        return null;
    }

    public List<Map<String,String>> getListValue(List<String> keys) {
        RBatch batch = redissonClient.createBatch();

        List<RFuture<Map<String, String>>> futures = new ArrayList<>();

        for (String key : keys) {
            RMapAsync<String, String> mapAsync = batch.getMap(key);
            futures.add(mapAsync.readAllMapAsync());
        }

        batch.execute();

        // 收集结果
        List<Map<String, String>> resultList = new ArrayList<>();
        for (RFuture<Map<String, String>> future : futures) {
            resultList.add(future.getNow());
        }
        return  resultList;
    }
}
