package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_follow")
public class FollowBean {
    @TableId(type = IdType.AUTO)
    public Integer id;

    public int bid;
    public int sid;

    @TableField(exist = false)
    public String  buser;

    @TableField(exist = false)
    public String  suser;

    @TableField(exist = false)
    public String store;

    @TableField(exist = false)
    public String logo;
}
