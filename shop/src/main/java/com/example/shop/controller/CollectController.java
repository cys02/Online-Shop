package com.example.shop.controller;

import com.example.shop.bean.CollectBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CollectMapper;
import com.example.shop.mapper.CommentMapper;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/collect")
public class CollectController extends BaseController{
    @Autowired
    CollectMapper collectMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",collectMapper.selectCollect());
        return "/collect/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        collectMapper.deleteById(id);
        return "redirect:/collect/list";//重定向，刷新
    }

//    @ResponseBody
//    @RequestMapping("/addcollect/vx")
//    public void addCollect(int uid,int pid){
//
//    }

    @ResponseBody
    @RequestMapping("/judcollect/vx")
    public VxResp judCollect(int uid,int pid){
        VxResp vx=new VxResp();
        List<CollectBean> collectBeanList = collectMapper.selectCollectByUidPid(uid,pid);
        if(collectBeanList.size()>0)vx.judcollect=1;
        else vx.judcollect=0;
        return vx;
    }

    @ResponseBody
    @RequestMapping("/addcollect/vx")
    public void addCollect(int uid,int pid){
        CollectBean collectBean = new CollectBean();
        collectBean.uid=uid;
        collectBean.pid=pid;
        System.out.println(uid+" "+pid);
        collectMapper.insert(collectBean);
    }

    @ResponseBody
    @RequestMapping("/deletecollect/vx")
    public void deleteCollect(int uid,int pid){
        collectMapper.deleteByUidPid(uid,pid);
    }


    @ResponseBody
    @RequestMapping("/getcollect/vx")
    public VxResp cartpage(int uid){
        VxResp vx=new VxResp();
        vx.collectProducts=collectMapper.selectVxCollect(uid);
        return vx;
    }
}
