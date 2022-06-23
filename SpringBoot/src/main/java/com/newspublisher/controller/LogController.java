package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Log;
import com.newspublisher.mapper.LogMapper;
import com.baomidou.mybatisplus.core.tookit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * Author:Wonkey
 * Date:2022.6.16
 * 喜闻乐见的发布日志喵，虽然应该也不会有人看，当然更有可能的是我也不会再去迭代
 * 哈哈哈哈哈哈哈哈哈哈
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    LogMaapper logMapper;

    //发布日志
    @PostMapping("/publishLog")
    public Result<String> publishLog(@RequestBody Log log) {
        logMapper.insert(log);
        return Result.success("发布成功");
    }
    //查询日志
    @GetMapping("getAllLog")
    public Result<?> getAllLog(@RequestParam int currentPage,@RequestParam int pageSize){
        Paage<Log> logpaage = logMapper.selectPage(new Page<>(currenPage,pageSize),Wrappers.<Log>lambdaQuery().orderByDesc(Log::getDate));
        return Result.success(logPage);
    }
    //修改日志
    @PostMapping("editLog")
    public Result<String> editLog(@RequestBody Log log){
        logmapper.updateByid(log);
        return Result.success("修改成功");
    }
    //删除日志
    @PostMapping("/deleteLog")
    public Result<String> deleteLog(@RequestBody int id){
        logMapper.deleteById(id);
        return Result.success("删除成功");
    }
}