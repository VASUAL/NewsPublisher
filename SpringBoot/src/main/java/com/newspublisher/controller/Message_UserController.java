package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Message_User;
import com.newspublisher.mapper.Message_UserMapper;
import com.baomidou.mybatisplus.core.tookit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * Author:Wonkey
 * Date:2022.6.16
 * 查询用户的私信状态，分为已读和未读
 */
@RestController
@RequestMapping("/message_User")
public class Message_UserController{
    @Resource
    Message_UserMapper message_userMapper;
    //未读私信
    @GetMapping("/getAallNotRead")
    public Result<?> getAllNotRead(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<Message_User> message_userPage = message_userMapper.selectPage(new page<>(currentPage,pageSize),Wrappers.<Message_User>lambdaQuery().eq(Message_User::getUserTo,userEmail).eq(Message_User::getState,0).orderByDesc(Message_User::getDate));
        return Result.success(message_userPage);
    }
    //已读私信
    @GetMapping("/getAallReaded")
    public Result<?> getAllReaded(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize){
        Page<Message_User> message_userPage = message_userMapper.selectPage(new Page<>(currentPage,pageSize),Wrappers.<Message_User>lambdaQuery().eq(Message_User::getUserTo,userEmail).eq(Message_User::getState,1).orderByDesc(Message_User::getDate));
        return Result.success(message_userPage);
    }
}