package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_order")
public class OrderBean {
//    @TableId(type = IdType.AUTO)
    @NotNull
    public Integer id;
    public String name;
    public String mobile;
    public String address;
    public Integer total;   //总价
    public Date ctime;      //下单时间
    public Integer uid;     //外键，关联的买家

    @TableField(exist = false)
    public String user; // 昵称

    @TableField(exist = false)
    public Integer count;

    @TableField(exist = false)
    public Integer pid;
}
