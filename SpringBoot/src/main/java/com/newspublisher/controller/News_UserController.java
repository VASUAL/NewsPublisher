package com.newspublisher.controller;

import com.newspublisher.common.Result;
import com.newspublisher.entity.News_User;
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
public class News_UserController {

    @Resource
    News_UserMapper news_userMapper;

    //查询所有新闻
    @GetMapping("/getAllNewsForNotFirst")
    public Result<?> getAllNewsForNotFirst(@RequestParam int currentPage,@RequestParam int pageSize) {
        Page<News_User> news_userPage = news_userMapper.selectPage(new Page<>(currentPage, pageSize), Wrappers.<News_User>lambdaQuery().eq(News_User::getFirst, 0).orderByDesc(News_User::getDate));
        return Result.success(news_userPage);
    }

    //根据新闻的编号查询新闻
    @PostMapping("/getNewsById")
    public Result<News_User> getNewsById(@RequestBody int id) {
        News_User news_user = news_userMapper.selectById(id);
        return Result.success(news_user);
    }

    //查询某板块的全部新闻
    @GetMapping("/getAllNewsByTypeName")
    public Result<?> getAllNewsByTypeName(@RequestParam String typeName,@RequestParam int currentPage,@RequestParam int pageSize) {
        Page<News_User> news_userPage = news_userMapper.selectPage(new Page<>(currentPage, pageSize), Wrappers.<News_User>lambdaQuery().eq(News_User::getType, typeName).orderByDesc(News_User::getDate));
        return Result.success(news_userPage);
    }

    //搜索所有新闻
    @GetMapping("/searchNewsForNotFirst")
    public Result<?> searchNewsForNotFirst(@RequestParam String search,@RequestParam int currentPage,@RequestParam int pageSize) {
        Page<News_User> news_userPage = news_userMapper.selectPage(new Page<>(currentPage, pageSize), Wrappers.<News_User>lambdaQuery().eq(News_User::getFirst, 0).like(News_User::getHeadline,search).orderByDesc(News_User::getDate));
        return Result.success(news_userPage);
    }

}
