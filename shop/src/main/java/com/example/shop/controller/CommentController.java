package com.example.shop.controller;

import com.example.shop.bean.CommentBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
    @Autowired
    CommentMapper commentMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",commentMapper.selectComment());
        return "/comment/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        commentMapper.deleteById(id);
        return "redirect:/comment/list";//重定向，刷新
    }


    @ResponseBody
    @RequestMapping("/addcomment/vx")
    public void addCommment(CommentBean commentBean){
//        System.out.println(commentBean.pid+" "+commentBean.uid+" "+commentBean.comment);
        commentBean.ctime= new Date();
        commentMapper.insert(commentBean);
    }
}
