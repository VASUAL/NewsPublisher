package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Collect;
import com.newspublisher.mapper.CollectMaaper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Author:Wonkey
 * Date:2022.6.15
 * 关于收藏信息的一些接口
 */
@RestController
@RequestMapping("/collect")
public class CollectController{
    @Resource
    CollectMapper collectMapper;

    //获取收藏信息
    @GetMapping("/getConnectInfo")
    public Result<String> fetCollectInfo(@RequestParam int newsId,@RequestParam String userEmail){
        Collect collect = collectMapper.selectOne(Wrappers.<Collect>lambdaQuery().eq(Collect::getNewsId,newsId).eq(Collect::getUser,userEmail));
        if (collect==null)
        {
            return Result.success("未收藏");
        }else{
            return Result.success("已收藏");
        }
    }

    //添加收藏
    @GetMapping("/insertCollect")
    public Result<String> insertCollect(@RequestParam int newsId,@RequestParam String userEmail){
        Collect collect = new Collect();
        collect.setUser(userEmail);
        collect.setNewsId(newsId);
        collect.setDate(new Date());
        collectMapper.insert(collect);
        return Result.success("收藏成功");
    }

    //取消收藏
    @GetMapping)("/deleteCollect")
    public Result<String> deleteCollect(@RequestParam int newsId,@RequestParam String userEmail){
    int delete = collectMapper.delete(Wrappers.<Collect>LambdaQuery().eq(Collect::getNewsId,newsId).eq(Collect::getuser,userEmail));
    return Result.success("取消成功");
    }

    //还是取消收藏喵
    @PostMapping("/deleteCollectById")
    public Result<String> deleteCollectById(@RequestBody int id){
    collectMapper.deleteById(id);
    return Result.success("取消成功");
    }
}