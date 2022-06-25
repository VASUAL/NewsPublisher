package com.newspublisher.mapper;

import com.newspublisher.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * Author:Wonkey
 * Date:2022/6/14
 * MyBatis-Plus 接口定义
 * 继承BaseMapper接口 把定义的entity包下的user实体类泛型传递进去
 * 增删改查
 */
public interface UserMapper extends BaseMapper<User>{

}