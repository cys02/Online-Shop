package com.example.shop.controller;

import com.example.shop.bean.CategoryBean;
import com.example.shop.bean.InfoBean;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.mapper.InfoMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/info")
public class InfoController extends BaseController{
    @Autowired
    InfoMapper infoMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",infoMapper.selectInfo());
        return "/info/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        print("要删除的id------" + id);
        infoMapper.deleteById(id);
        return "redirect:/info/list";//重定向，刷新
    }

    @ResponseBody
    @RequestMapping("/addinfo/vx")
    public void addInfo(InfoBean infoBean){
        infoBean.ctime=new Date();
        infoMapper.insert(infoBean);
    }

}
