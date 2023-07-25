package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CartBean;
import com.example.shop.bean.CollectBean;
import com.example.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<CartBean> {
    @Select("select * from view_cart")
    List<CartBean> selectCart();

    @Select("select * from view_vxcart WHERE bid=#{bid}")
    List<ProductBean> selectVxCart(int bid);

    @Select("select * from tbl_cart WHERE bid=#{bid} AND pid=#{pid}")
    List<CartBean> selectCartByPidUid(int bid,int pid);
}
