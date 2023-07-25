package com.example.shop.bean;

import java.util.List;

//VxResp是专门给小程序准备的，给小程序返回的数据都在这里定义
public class VxResp {
    public int code=1;    //响应码，1成功，0失败，默认成功
    public String msg="";  //错误提示语，默认空字符串

    public Integer judcollect=0;

    public Integer judfollow=0;
    //失败函数
    public void fail(String msg){
        this.code = 0;
        this.msg=msg;
    }

    public String uid = ""; //给小程序的uid
    public List<CategoryBean> categorys;
    public List<ProductBean> hots;
    public List<ProductBean> products;
    public ProductBean product;
    public List<ProductBean> cartProducts;

    public List<ProductBean> xb;
    public List<ProductBean> xl;
    public List<ProductBean> wb;
    public List<ProductBean> zb;
    public List<CommentBean> comments;
    public List<ShoppingBean> shoppings;
    public List<ProductBean> collectProducts;

    public List<UserBean> salers;
    public List<ProductBean> productBySid;

    public List<FollowBean> followInfo;
}
