package com.example.shop.controller;

import com.example.shop.bean.ShoppingBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.InfoMapper;
import com.example.shop.mapper.ShoppingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/shopping")
public class ShoppingController extends BaseController{
    @Autowired
    ShoppingMapper shoppingMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",shoppingMapper.selectShopping());
        return "/shopping/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        shoppingMapper.deleteById(id);
        return "redirect:/shopping/list";//重定向，刷新
    }


    @ResponseBody
    @RequestMapping("/getshopping/vx")
    public VxResp getShopping(int uid){
        VxResp vx=new VxResp();
        vx.shoppings=shoppingMapper.selectVxShoppingById(uid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(ShoppingBean shoppingBean: vx.shoppings){
            shoppingBean.total=shoppingBean.price*shoppingBean.count;
            shoppingBean.ftime = sdf.format(shoppingBean.cime);
        }
        return vx;
    }
}
