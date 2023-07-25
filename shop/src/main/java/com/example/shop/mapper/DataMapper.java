package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.DataOneBean;
import com.example.shop.bean.InfoBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataMapper extends BaseMapper<InfoBean> {
//    @Select("select * from view_info")
//    List<InfoBean> selectInfo();
    @Select("select * from view_num_by_category")
    List<DataOneBean> selectDataOne();
}
