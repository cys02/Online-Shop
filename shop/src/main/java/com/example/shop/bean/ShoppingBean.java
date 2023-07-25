package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_shopping")
public class ShoppingBean {
    @TableId(type = IdType.AUTO)
    public Integer id;

    public Integer count;
    public Integer pid; //商品id
    public Integer oid; //关联的订单号

    @TableField(exist = false)
    public String name; //买家姓名
    @TableField(exist = false)
    public String product; //商品名称
    @TableField(exist = false)
    public String logo;
    @TableField(exist = false)
    public Integer total;
    @TableField(exist = false)
    public String uid;
    @TableField(exist = false)
    public Integer price;
    @TableField(exist = false)
    public Date cime;
    @TableField(exist = false)
    public String ftime;
}
