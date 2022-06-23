package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Comment_News;
import com.newspublisher.mapper.Comment_NewsMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Author:Wonkey
 * Date:2022.6.15
 * 查询用户的所有评论
 */
@RestController
@RequestMapping("/comment_News")
public class Comment_newsController{
    @Resource
    Comment_NewsMapper comment_newsMapper;
    @GetMapping("/get")
    public Result<?> get(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<Comment_News> comment_newsPage = comment_newsMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<Comment_News>.lambdaQuery().eq(Comment_News::getAuthor,userEmail).orderByDesc(Comment_News::getDate));
        return Result.success(comment_newsPage);
    }
}