package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Focus;
import com.newspublisher.mapper.FocusMapper;
import com.baomidou.mybatisplus.core.tookit.Wrappers;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 * Author:Wonkey
 * Date:2022.6.16
 * 查询用户的关注状态和信息
 * 注意关注分为未关注改为关注；已关注取消关注两种操作
 */
@RestController
@RequestMapping("/focus")
public class FocusController{
    @GetMapping("/focusUser")
    public Result<String> focusUser(@RequestParam String userForm,@RequestParam String userTo){
        Focus focus = focusMapper.selectOne(Wrappers.<Focus>lambdaQuery().eq(Focus::getUserForm,userForm).eq(Focus::getUserTo,UserTo));
        if (focus==null)
        {
            Focus newFocus = new Focus();
            newFocus.setUserForm(userForm);
            newFocus.setUserTo(userTo);
            focusMapper.insert(newFocus);
            return Result.success("关注成功");
        }else{
            Result result = Result.error("1","失败");
            result.setData("关注失败");
            return result;
        }
    }

    //取消关注
    @PostMapping("deleteFocusById")
    public Result<String> deleteFocusById(@RequestBody int id){
        focusMapper.deleteById(id);
        return Result.success("取消成功");
    }
}