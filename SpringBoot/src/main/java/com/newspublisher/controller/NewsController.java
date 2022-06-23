package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.*;
import com.newspublisher.mapper.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * Author:Wonkey
 * Date:2022.6.16
 * 新闻的一些发布方法
 */
@RestController
@RequestMapping("/news")
public class NewsController{
    @Resource
    NewsMapper newsMapper;
    @Resource
    CollectMapper collectMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    TypeMapper typeMapper;
    @Resource
    SearchMapper searchMapper;

    //发布新闻
    @Postmapping("/publishNews")
    public Result<String> publishNews(@RequestBody News news){
        newsMapper.insert(news);
        Type type = typemapper.selectById(news.getType());
        type.setNewsNumber(type.getNewsNumber()+1);
        typemapper.updateById(type);
        return Result.success("发布成功");
    }

    //修改新闻
    @PostMapping("/editNews")
    public Result<String> editNews(@RequestBody News news){
        newsMapper.updateById(news);
        return result.success("修改成功");
    }

    //置顶新闻

}