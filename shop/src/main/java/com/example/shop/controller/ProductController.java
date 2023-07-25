package com.example.shop.controller;

import com.example.shop.bean.CommentBean;
import com.example.shop.bean.ProductBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.mapper.CommentMapper;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.util.NotNull;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping("/index/vx")
    public VxResp index(Integer cid) {
        VxResp vx = new VxResp();
        vx.categorys = categoryMapper.selectList(null);
        vx.hots = productMapper.selectHot();
        if (cid == null && !vx.categorys.isEmpty()) {
            //cid没有，有类别
            cid = vx.categorys.get(0).id;
        }
        if (cid == null) {
            //cid没传，类别数组也是空的，没有类别、没有商品
            vx.products = new ArrayList<>();    //空的商品数组
        } else {
            //有类别，按照类别id查找这个类别的商品列表
            vx.products = productMapper.selectProduct(cid);//cid是类别id
        }
        //时间格式化工具
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (ProductBean bean : vx.products) {
            bean.ftime = sdf.format(bean.ctime);//Date类型的ctime转换为String类型的ftime
        }
        return vx;
    }

    //vx?pid=num
    @ResponseBody   //小程序地址必须要加这个注解
    @RequestMapping("/detail/vx")
    public VxResp detail(int pid) {
        VxResp vx = new VxResp();
        vx.product = productMapper.selectById(pid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        vx.product.ftime = sdf.format(vx.product.ctime);

        vx.comments=commentMapper.selectCommentById(pid);
        for(CommentBean commentBean: vx.comments){
            commentBean.ftime=sdf.format(commentBean.ctime);
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/show/vx")
    public VxResp show(){
        VxResp vx = new VxResp();

        vx.xb=productMapper.selectProduct(9);
        vx.xl=productMapper.selectProduct(8);
        vx.zb=productMapper.selectProduct(10);
        vx.wb=productMapper.selectProduct(11);

        return vx;
    }

    @RequestMapping("/all")
    public String all(HttpServletRequest req) {  //想要查询视图中的数据
        req.setAttribute("retList", productMapper.selectView());
        return "/product/all";
    }

    @RequestMapping("/list")
    public String list(int uid, HttpServletRequest req) {
        req.setAttribute("retList", productMapper.selectList(uid));
        return "/product/list";
    }

    @RequestMapping("/del")
    public String del(int id) {
        //baseMapper中本来就有的，不用写sql
        int uid = productMapper.selectById(id).uid;
//        productMapper.deleteById(id);
        productMapper.transferhide(id);
        return "redirect:/product/list?uid=" + uid;//重定向，刷新
    }

    @GetMapping("/add") //点按钮进来的
    public String add(Integer id, HttpServletRequest req) {
        //如果id是null，bean就是null；否则bean是id对应的记录
        req.setAttribute("cateList", categoryMapper.selectList(null));
        req.setAttribute("bean", id == null ? null : productMapper.selectById(id));
        return "/product/add";  //转发到templates/product/add.html
    }

    //@RequestMapping什么请求方式都可以进来
    //@GetMapping只有get请求能进来

    @PostMapping("/add")
    public String add(ProductBean bean, HttpServletResponse resp) {
        //System.out.println("我是表单进来的");
        bean.hide = 0;
        bean.description = "";
        if (NotNullUtil.isBlank(bean)) {
            return jsAlert("请完善信息！", resp);
        }
        if (bean.id == null) {//为空是添加操作
            bean.ctime = new Date();
            productMapper.insert(bean);//插入数据库，用的是BaseMapper，没写SQL
        } else {//不为空是修改操作
            productMapper.updateById(bean);
        }
        return "redirect:/product/list?uid=" + bean.uid;
    }

    @ResponseBody
        @RequestMapping("getproductbysid/vx")
    public VxResp getProductByUid(int sid){
        VxResp vx=new VxResp();
        vx.productBySid=productMapper.selectBySid(sid);
        return vx;
    }

}
