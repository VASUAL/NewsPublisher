package com.newspublisher.entity;
/**
 * Author :Wonkey
 * Date:2022/6/14
 * user实体类
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class User {
    @TableId(value = "email")
    private String email;
    private String petname;
    private String sex;
    private Date birthday;
    private String area;
    private int role;
}
