package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Collect_news;
import com.newspublisher.mapper.Collect_NewsMaaper;
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
 * 查询用户的全部收藏信息
 */
@RestController
@RequestMapping("/collect_News")
public class Collect_NewsController{
    @Resouce
    Collect NewsMapper collect newsMapper;
    @GetMapping("/get")
    public Result<?> get(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<Collect_News> collect_newsPage = collect_newsMapper.selectPaage(new Page<>(currentPage,pageSize),Wrappers.<Collect_News>lambdaQuery().eq(Collect_News::getUser,useEmail).orderByDesc(Collect_News::getDate));
        return Result.success(collect_newsPage);
    }
}