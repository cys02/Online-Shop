package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.InfoBean;
import com.example.shop.bean.OrderBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderBean> {
    @Select("select * from view_order")
    List<OrderBean> selectOrder();
}
