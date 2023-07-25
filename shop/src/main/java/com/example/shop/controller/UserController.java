package com.example.shop.controller;

import com.example.shop.bean.UserBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.UserMapper;
import com.example.shop.util.FileUtil;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;

@Controller // 使这个类成为控制器
public class UserController {
    @Autowired // 注入，就像是new
    UserMapper userMapper;
    @ResponseBody   //把java对象转成字符串
    @RequestMapping("/login/vx")
    public VxResp login(UserBean bean){
        VxResp vx=new VxResp();
        if(NotNullUtil.isBlank(bean)){
            vx.fail("请完善信息！");
            return vx;
        }
        //不为空的情况
        bean.status="买家";
        if(userMapper.haveUser(bean.username)!=null){
            //账号已经存在了
            System.out.println(bean.username+" "+bean.password+" "+bean.status);
            UserBean user=userMapper.getUser(bean);
            if(user==null){
                vx.fail("您的密码错误！");
            }
            else {
                //登陆成功的这个人的id返回给小程序
                vx.uid=String.valueOf(user.id);
            }
        }else{
            //没有账号
            //偷懒，昵称和用户名一样
            bean.user = bean.username;
            userMapper.insert(bean);//新人注册到用户表中
            vx.uid=String.valueOf(bean.id);
        }

        return vx;  //把对象转成字符串返回给java
    }

    // 给变量起名字用英语单词  因为java是编译型语言
    //    http://localhost:8080/login?username=xxx&password=111
    @RequestMapping("/login") // 抛给了Tomcat吸收
    public String login(UserBean bean, HttpServletRequest req) throws Exception {
        bean.status="卖家";
        UserBean user = userMapper.getUser(bean);
        if (user == null) {
            System.out.println("用户名或密码错误");
            return "redirect:/index.html?msg=" + URLEncoder.encode("用户名或密码错误", "utf-8"); // 重定向
        } else {
            System.out.println("success!");
            req.setAttribute("user",user);
            return "redirect:/main?uid=" + user.id;
        }
    }

    @RequestMapping("/main")
    public String main(int uid,HttpServletRequest req){
        System.out.println(uid);

        req.setAttribute("user",userMapper.selectById(uid));
        return "/main";
    }

    //转发打开templates中的网页,template只能通过java处理，转发打开
    //重定向打开static中的网页

    //localhost:8080/upload
    @ResponseBody
    @RequestMapping("/upload")  //Tomcat吸收错误
    public String upload(MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        FileUtil.createFile("N:/cys/create/shop/upload");
        file.transferTo(new File("N:/cys/create/shop/upload/" + fileName));
        //转发，重定向
        return "/shop/upload/" + fileName;
    }

    @ResponseBody
    @RequestMapping("/getsalers/vx")
    public VxResp getSalers(){
        VxResp vx=new VxResp();
        vx.salers=userMapper.getSalers();
        return vx;
    }

}
