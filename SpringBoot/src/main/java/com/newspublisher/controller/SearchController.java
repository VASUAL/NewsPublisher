package com.newspublisher.controller;

import com.newspublisher.common.Result;
import com.newspublisher.entity.Search;
import com.newspublisher.mapper.SearchMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * Author:Wonkey
 * Date:2022.6.20
 * 关于搜索框的操作
 * 之所以不放在NewsController中一起写了是因为该接口涉及一个不一样的表
 * 即搜索记录表
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    SearchMapper searchMapper;

    //该接口的作用是清空搜索记录
    @PostMapping("/clearUserSearch")
    public Result<String> clearUserSearch(@RequestBody String userEmail) {
        searchMapper.delete(Wrappers.<Search>lambdaQuery().eq(Search::getUser,userEmail));
        return Result.success("删除成功");
    }

    //该接口的作用是将用户的搜索内容存入数据库
    @GetMapping("/saveUserSearchContent")
    public Result<String> saveUserSearchContent(@RequestParam String userEmail,@RequestParam String searchContent) {
        List<Search> searches = searchMapper.selectList(Wrappers.<Search>lambdaQuery().eq(Search::getUser, userEmail));
        boolean contained = false;  //是否已经搜索过
        for (Search s : searches) {
            if (s.getContent().equals(searchContent)) {
                contained = true;
                break;
            }
        }
        if (!contained) {
            Search search = new Search();
            search.setUser(userEmail);
            search.setContent(searchContent);
            searchMapper.insert(search);
        }
        return Result.success("操作成功");
    }

    //该接口的作用是删除一条搜索记录
    @PostMapping("/deleteOneSearchById")
    public Result<String> deleteOneSearchById(@RequestBody int id) {
        searchMapper.deleteById(id);
        return Result.success("删除成功");
    }

    //该接口的作用是查询某用户的全部搜索记录
    @GetMapping("/getAllSearchByUserEmail")
    public Result<?> getAllSearchByUserEmail(@RequestParam String userEmail,@RequestParam int currentPage,@RequestParam int pageSize) {
        Page<Search> searchPage = searchMapper.selectPage(new Page<>(currentPage, pageSize), Wrappers.<Search>lambdaQuery().eq(Search::getUser, userEmail));
        return Result.success(searchPage);
    }
}