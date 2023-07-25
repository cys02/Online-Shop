package com.example.shop.controller;

import com.example.shop.bean.DataOneBean;
import com.example.shop.bean.Data;
import com.example.shop.mapper.DataMapper;
import com.example.shop.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController extends BaseController{
    @Autowired
    DataMapper dataMapper;
//    @RequestMapping("/list")
//    public String list(HttpServletRequest req){
////        req.setAttribute("retList",infoMapper.selectInfo());
//        return "/data/list";
//    }

    @RequestMapping("/list")
    public String dataOne(HttpServletRequest req){
        List<DataOneBean> dataOneBeanList = dataMapper.selectDataOne();
        List<String> category = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        req.setAttribute("category",category);
        req.setAttribute("nums",nums);
        return "/data/list";
    }



}
