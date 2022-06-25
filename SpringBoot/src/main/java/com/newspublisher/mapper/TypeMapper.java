package com.newspublisher.mapper;

import com.newspublisher.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * Author:Wonkey
 * Date:2022/6/21
 * MyBatis-Plus 接口定义
 * 继承BaseMapper接口 把定义的entity包下的type实体类泛型传递进去
 * 增删改查
 */
public interface TypeMapper extends BaseMapper<Type>{

}