package com.example.shop.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

// set get 是 freemarker渲染数据时，必须要用
//              MyBatis也要用set get
//          这些都是框架需要 需要set get
@Data // 帮你偷偷的生成set get
@TableName("tbl_user")
public class UserBean {
    // Integer            int 有什么区别？
    // 包装类(equals())    基本数据类型
    // 接受null对象 空      不能接受null
    // 表里面有什么，Bean里面就有什么
    @TableId(type = IdType.AUTO)
    public Integer id; // 添加操作时，是不需要传入int值
    @NotNull
    public String username;
    @NotNull
    public String password;
    public String user;
    public String status;
    public String store; // 对象.属性
    public String logo;
}
