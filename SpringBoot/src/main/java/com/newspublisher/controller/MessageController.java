package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Message;
import com.newspublisher.mapper.MessageMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;
/**
 * Author:Wonkey
 * Date:2022.6.16
 * 管理员对于用户评论进行的删除，回复操作
 * 注意先遍历Mapper
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    MessageMapper messageMapper;
    //管理员发送私信
    @Postmapping("/sendMessageToAllUser")
    public Result<String> sendMessageToAllUser(@RequestBody Message message){
        messageMapper.insert(message);
        return result.success("发送成功");
    }
    //发送私信
    @postmapping("/sendMessage")
    public Result<String> sendMessage(@RequestBody Message message){
        message.setDate(new Date());
        messageMapper.insert(message);
        return Result.success("发送成功");
    }
    //已读私信状态
    @PostMapping("/setMessageReaded")
    public Result<String> setMessageReaded(@RequestBody int id){
        Message message = messageMapper.selectById(id);
        message.setState(1);
        messageMapper.updateById(message);
        return Result.success("更新成功");
    }
    //删除私信
    @PostMapping("/deleteMessage")
    public Result<String> deleteMessage(@RequestBody int id)
    {
        messageMapper.deleteById(id);
        return Result.success("删除成功");
    }
}