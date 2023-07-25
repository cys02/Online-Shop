package com.example.shop.controller;

import com.example.shop.bean.CartBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CartMapper;
import com.example.shop.mapper.CollectMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{
    @Autowired
    CartMapper cartMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",cartMapper.selectCart());
        return "/cart/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        cartMapper.deleteById(id);
        return "redirect:/cart/list";//重定向，刷新
    }

    @ResponseBody
    @RequestMapping("/vxadd")
    public VxResp addCart(int id, int uid){
        VxResp vx=new VxResp();
        int pid=id,bid=uid;
        List<CartBean> cartBeanList=cartMapper.selectCartByPidUid(bid, pid);
        System.out.println(cartBeanList);
        System.out.println("查到的表中有："+cartBeanList.size());
        if(!cartBeanList.isEmpty()){
            CartBean cartBean = cartBeanList.get(0);
            cartBean.count++;
            cartMapper.updateById(cartBean);
        }
        else {
            CartBean cartBean=new CartBean();
            cartBean.pid=pid;
            cartBean.bid=bid;
            cartBean.count=1;
            cartMapper.insert(cartBean);
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/vxcart")
    public VxResp cartpage(int uid){
        VxResp vx=new VxResp();
        vx.cartProducts=cartMapper.selectVxCart(uid);
        return vx;
    }

}
