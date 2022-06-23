package com.newspublisher.controller;

import com.newspublisher.commom.Result;
import com.newspublisher.entity.Comment;
import com.newspublisher.mapper.CommentMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
/**
 * Author:Wonkey
 * Date:2022.6.15
 * 关于评论的一些操作，包括增删改查
 * 请先配置Mapper
 */
@RestController
@RequestMapping("/comment")
public class Collect_NewsController{
    @Resouce
    CommentMapper commentMapper;

    //新增评论
    @Postmapping("insertComment")
    public Result<String> insertComment(@RequestBody Comment comment){
        comment.setDate(new Date());
        commentMapper.insert(comment);
        return  Result.success("新增成功");
    }
    //删除评论
    @deleteMapping("/deleteCommentById")
    public Result<String> deleteCommentById(@RequestBody int id){
        commentMapper.deleteById(id);
        return Result.success("删除成功");
    }
}