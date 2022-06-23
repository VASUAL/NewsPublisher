package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Focus_User;
import com.newspublisher.mapper.Focus_UserMapper;
import com.baomidou.mybatisplus.core.tookit.Wrappers;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
/**
 * Author:Wonkey
 * Date:2022.6.15
 * 查询用户的全部关注
 * 请先配置Mapper
 */
@RestController
@RequestMapping("/focus_User")
public class Focus_UserController{
    @Resouce
    Focus_UserMapper focus_UserMapper;

    //查询用户的全部关注
    @GetMapping("/get")
    public Result<?> get(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<Focus_User> focus_userPage = focus_UserMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<Focus_User>lambdaQuery().eq(Focus_User::getUserForm,userEmail));
        return Result.success(focus_userPage);
    }
}