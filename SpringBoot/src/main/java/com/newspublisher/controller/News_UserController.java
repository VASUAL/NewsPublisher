package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.News_user;
import com.newspublisher.mapper.News_UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * Author:Wonkey
 * Date:2022.6.16
 * 查询置顶新闻
 */
@RestController
@RequestMapping("/news_User")
public class News_UserController{
    @Resource
    News_UserMapper news_userMapper;

    //查询非置顶新闻
    @GetMapping("/getAllNewsForNotFirst")
    public Result<?> getAllNewsForNotFirst(@requestParam int currentPage,@RequestParam int pageSize){
        Page<News_User> news_userPage = news_userMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<News_User>lambdaQuery().eq(News_User::getFirst,0).orderByDesc(News_User::getDate());
        return Result.success(news_userpage);
    }

    //查询置顶新闻
    @GetMapping("/getAllNewsForFirst")
    public Result<?> getAllNewsForFirst(@RequestParam int currentPage,@RequestParam int pageSize){
        Page<News_User> news_userPage = news_userMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<News_User>lambdaQuery().eq(News_User::getFirst,1).orderByDesc(News_User::getDate));
    }

    //新闻的编号查询新闻
    @Postmapping("/getNewsById")
    public Result<News_User> getNewsById(@RequestBody int id){
        New_User news_user = news_userMapper.selectById(id);
        return result.success(news_user);
    }

    //查询某板块的新闻
    @GetMapping("/getAllNewsByTypeName")
    public Result<?> getAllNewsByTypeName(@RequestParam String typeName,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<News_User> news_userPage = news-news_userMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<News_User>lambdaQuery().eq(News_User::getType,typeName).orderByDesc(News_User::getDate));
        return Result.success(news_userPage);
    }
    //搜索非置顶新闻
    @GetMapping("/searchNewsForNotFirst")
    public Result<?> searchNewsForNotFirst(@RequestParam String search,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<News_User> news_userPage=news_userMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<News_User>lambdaQuery().eq(News_User::getFirst,0).like(News_user::getHeadline,search).orderByDesc(News_User::getDate));
        return Result.success(news_userPage);
    }

    //搜索置顶新闻
    @GetMapping("/searchNewsForFirst")
    public Result<?> searchNewsForFirst(@RequestParam String search,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<News_User> news_userPage = news_userMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<News_User>lambdaQuery().eq(News_user::getFirst,1).like(News_User::getHeadline,search).orderByDesc(News_User::getDate));
        return Result.success(news_userPage);
    }
}