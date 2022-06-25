package com.newspublisher.controller;

import com.newspublisher.common.Result;
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
 * Date:2022.6.16&&2022.6.20
 * 新闻的一些发布,置顶，删除方法
 * 对于搜索框的一些操作
 * 注意采用的是简单的关键词分析方法，并没有涉及到智能推荐方法
 * 可在此进行展开，以达到毕设要求
 * 所有搜索均不是模糊搜索
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    NewsMapper newsMapper;
    @Resource
    TypeMapper typeMapper;
    @Resource
    SearchMapper searchMapper;

    //发布新闻
    @PostMapping("/publishNews")
    public Result<String> publishNews(@RequestBody News news) {
        newsMapper.insert(news);
        Type type = typeMapper.selectById(news.getType());
        type.setNewsNumber(type.getNewsNumber()+1);
        typeMapper.updateById(type);
        return Result.success("发布成功");
    }

    //修改新闻
    @PostMapping("/editNews")
    public Result<String> editNews(@RequestBody News news) {
        newsMapper.updateById(news);
        return Result.success("修改成功");
    }


    //删除新闻
    @PostMapping("/deleteNews")
    public Result<String> deleteNews(@RequestBody int id) {
        Type type = typeMapper.selectById(newsMapper.selectById(id).getType());
        type.setNewsNumber(type.getNewsNumber()-1);
        typeMapper.updateById(type);
        newsMapper.deleteById(id);
        return Result.success("删除成功");
    }

    //该接口的作用是使该新闻的点击量加1
    @PostMapping("/increaseNewsClick")
    public Result<String> increaseNewsClick(@RequestBody int newsId) {
        News news = newsMapper.selectById(newsId);
        news.setClick(news.getClick()+1);
        newsMapper.updateById(news);
        return Result.success("增加成功");
    }

    //该接口的作用是基于用户搜索记录进行兴趣推荐
    @PostMapping("/getNewsByUserSearch")
    public Result<List<News>> getNewsByUserSearch(@RequestBody String userEmail) {
        List<Search> searches = searchMapper.selectList(Wrappers.<Search>lambdaQuery().eq(Search::getUser, userEmail));
        List<News> news = newsMapper.selectList(Wrappers.<News>lambdaQuery().orderByDesc(News::getClick));
        List<News> list = new ArrayList<>();
        for (News n : news) {
            for (Search s : searches) {
                if (n.getHeadline().contains(s.getContent())||n.getContent().contains(s.getContent())) {
                    list.add(n);
                    break;
                }
            }
        }
        if (list.size()==0) {
            list = news;
        }else if (list.size()>10) {
            list = list.subList(0,9);
        }
        return Result.success(list);
    }

    //该接口的作用是查询热门新闻，返回前20条
    @PostMapping("/getHotNews")
    public Result<List<News>> getHotNews() {
        List<News> list = newsMapper.selectList(Wrappers.<News>lambdaQuery().orderByDesc(News::getClick));
        if (list.size()>20) {
            list = list.subList(0,19);
        }
        return Result.success(list);
    }

    //该接口的作用是查询最新新闻，返回前20条
    @PostMapping("/getLatestNews")
    public Result<List<News>> getLatestNews() {
        List<News> list = newsMapper.selectList(Wrappers.<News>lambdaQuery().orderByDesc(News::getDate));
        if (list.size()>20) {
            list = list.subList(0,19);
        }
        return Result.success(list);
    }

    //该接口的作用是已知板块名查询该板块的前10个新闻
    @PostMapping("/getNewsByNewsType")
    public Result<List<News>> getNewsByNewsType(@RequestBody String type) {
        List<News> list = newsMapper.selectList(Wrappers.<News>lambdaQuery().eq(News::getType, type).orderByDesc(News::getDate));
        if (list.size()>10) {
            list = list.subList(0,9);
        }
        return Result.success(list);
    }

    //该接口的作用是根据搜索内容返回数据
    @GetMapping("/searchNews")
    public Result<?> searchNews(@RequestParam String searchContent,@RequestParam int currentPage,@RequestParam int pageSize) {
        Page<News> newsPage = newsMapper.selectPage(new Page<>(currentPage, pageSize), Wrappers.<News>lambdaQuery().like(News::getHeadline, searchContent).or().like(News::getContent, searchContent).or().like(News::getType, searchContent));
        return Result.success(newsPage);
    }
}