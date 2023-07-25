package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.InfoBean;
import com.example.shop.bean.ShoppingBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoppingMapper extends BaseMapper<ShoppingBean> {
    @Select("select * from view_shopping")
    List<ShoppingBean> selectShopping();

    @Select("select * from view_vxshopping WHERE uid=#{uid}")
    List<ShoppingBean> selectVxShoppingById(int uid);
}
