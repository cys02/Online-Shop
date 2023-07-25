package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CategoryBean;
import com.example.shop.bean.InfoBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InfoMapper extends BaseMapper<InfoBean> {
    @Select("select * from view_info")
    List<InfoBean> selectInfo();
}
