package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Comment_User;
import com.newspublisher.mapper.Comment_UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:Wonkey
 * Date:2022.6.15
 * 查询用户的所有评论
 */
@RestController
@RequestMapping("/comment_User")
public class Comment_newsController{
    @Resource
    Comment_NewsMapper comment_newsMapper;
    @GetMapping("/get")
    public Result<?> get(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<Comment_News> comment_newsPage = comment_newsMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<Comment_News>.lambdaQuery().eq(Comment_News::getAuthor,userEmail).orderByDesc(Comment_News::getDate));
        return Result.success(comment_newsPage);
    }
}