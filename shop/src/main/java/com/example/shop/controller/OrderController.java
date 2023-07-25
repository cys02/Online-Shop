package com.example.shop.controller;

import com.example.shop.bean.OrderBean;
import com.example.shop.bean.ProductBean;
import com.example.shop.bean.ShoppingBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.InfoMapper;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.mapper.ShoppingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ShoppingMapper shoppingMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",orderMapper.selectOrder());
        return "/order/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        orderMapper.deleteById(id);
        return "redirect:/order/list";//重定向，刷新
    }

    @ResponseBody
    @RequestMapping("/addorder/vx")
    public void addOrder(OrderBean orderBean){
        int pid=orderBean.pid;
        int count=orderBean.count;
        ProductBean productBean=productMapper.selectById(pid);
        orderBean.total=productBean.price*count;
        orderBean.ctime=new Date();
        orderMapper.insert(orderBean);

        ShoppingBean shoppingBean=new ShoppingBean();
        shoppingBean.count=count;
        shoppingBean.pid=pid;
        shoppingBean.oid=orderBean.id;

        shoppingMapper.insert(shoppingBean);
    }




}
