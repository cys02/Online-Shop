package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@Data
@TableName("tbl_product")//CRUD

public class ProductBean {
    @TableId(type = IdType.AUTO)
    public Integer id;
    //主键：1. 自动增长
    //     2. UUID
    @NotNull    //非空
    public String product;
    @NotNull
    public Integer price;
    @NotNull
    public String logo; //数据库保存图片的地址；可以把图片通过base64算法转化为字符串
    public String hot; //热卖
    public Date ctime;
    public String description;
    @NotNull
    public Integer num;
    public Integer uid; //外键 人 谁在卖
    public Integer cid;//外键 类别 所属类别id
    public Integer hide;

    //这两个是从视图中查询出来的，原表中没有
    @TableField(exist = false)
    public String user;
    @TableField(exist = false)
    public String category;
    @TableField(exist = false)  //数据库中没有这个字段
    public String ftime;    //默认是空的

    @TableField(exist = false)
    public int count;

}
