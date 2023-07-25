package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_cart")
public class CartBean {
    @TableId(type = IdType.AUTO)
    public Integer id;
    public int count;
    public int pid;
    public int bid;

    @TableField(exist = false)
    public String user;
    @TableField(exist = false)
    public String product;
}

