package com.newspublisher.entity;
/**
 * Author :Wonkey
 * Date:2022/6/21
 * Type实体类
 */
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("type")
@Data
public class Type {
    @TableId(value = "name")
    private String name;
    private int newsNumber;
    private int state;
}