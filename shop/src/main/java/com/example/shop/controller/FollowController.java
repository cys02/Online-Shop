package com.example.shop.controller;

import com.example.shop.bean.FollowBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.FollowMapper;
import com.example.shop.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/follow")
public class FollowController extends BaseController{
    @Autowired
    FollowMapper followMapper;
    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",followMapper.selectFollow());
        return "/follow/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
        followMapper.deleteById(id);
        return "redirect:/follow/list";//重定向，刷新
    }

    @ResponseBody
    @RequestMapping("/judfollow/vx")
    public VxResp judFollow(int bid,int sid){
        System.out.println();
        VxResp vx=new VxResp();
        List<FollowBean> followBeanList = followMapper.selectFollowByBidSid(bid,sid);
        if(followBeanList.size()>0)vx.judfollow=1;
        else vx.judfollow=0;
        return vx;
    }

    @ResponseBody
    @RequestMapping("/addfollow/vx")
    public void addcollect(int bid,int sid){
        FollowBean followBean=new FollowBean();
        followBean.bid=bid;
        followBean.sid=sid;
        followMapper.insert(followBean);
    }

    @ResponseBody
    @RequestMapping("/deletefollow/vx")
    public void deleteFollow(int bid,int sid){
        followMapper.deleteByBidSid(bid,sid);
    }

    @ResponseBody
    @RequestMapping("/getfollow/vx")
    public VxResp getFollow(int bid){
        VxResp vx = new VxResp();
        vx.followInfo = followMapper.selectvxFollow(bid);
        return vx;
    }


}
