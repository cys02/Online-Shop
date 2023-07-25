package com.example.shop.controller;

import com.example.shop.bean.CategoryBean;
import com.example.shop.bean.Data;
import com.example.shop.bean.DataOneBean;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    @Autowired
    CategoryMapper categoryMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        req.setAttribute("retList",categoryMapper.selectList(null));
        return "/category/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        //baseMapper中本来就有的，不用写sql
//        categoryMapper.deleteById(id);
        categoryMapper.transferhide(id);
        return "redirect:/category/list";//重定向，刷新
    }

    @GetMapping("/add") //点按钮进来的
    public String add(Integer id, HttpServletRequest req){
        //如果id是null，bean就是null；否则bean是id对应的记录
        req.setAttribute("cateList",categoryMapper.selectList(null));
        req.setAttribute("bean", id==null?null:categoryMapper.selectById(id));
        return "/category/add";  //转发到templates/category/add.html
    }

    //@RequestMapping什么请求方式都可以进来
    //@GetMapping只有get请求能进来

    @PostMapping("/add")
    public String add(CategoryBean bean, HttpServletResponse resp) {
        if(NotNullUtil.isBlank(bean)){
            return jsAlert("请完善信息！",resp);
        }
        bean.hide=0;
        try{
            if(bean.id==null) {//为空是添加操作
//            bean.ctime = new Date();
                categoryMapper.insert(bean);//插入数据库，用的是BaseMapper，没写SQL
            }
            else {//不为空是修改操作
                categoryMapper.updateById(bean);
            }
        }catch (Exception e){
            return jsAlert("类别名称不能重复",resp);
        }

        return "redirect:/category/list";
    }

    @ResponseBody
    @RequestMapping("/getdata")
    public Data getdata(){
        Data data=new Data();
        data.xAxisData=new ArrayList<>();
        data.seriesData=new ArrayList<>();
        List<CategoryBean> dataOneBeanList = categoryMapper.selectDataOne();
        System.out.println(dataOneBeanList);
        for(CategoryBean categoryBean:dataOneBeanList){
//            System.out.println(categoryBean.category+" "+categoryBean.nums);
            data.xAxisData.add(categoryBean.category);
            data.seriesData.add(categoryBean.nums);
        }
        return data;
    }

}
