package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_collect")
public class CollectBean {
    @TableId(type = IdType.AUTO)
    public Integer id;
    public int pid;
    public int uid;

    @TableField(exist = false)
    public String user;
    @TableField(exist = false)
    public String product;


}
