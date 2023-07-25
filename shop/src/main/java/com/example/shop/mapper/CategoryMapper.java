package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CategoryBean;
import com.example.shop.bean.DataOneBean;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapper extends BaseMapper<CategoryBean> {
    @Update("update tbl_category set hide=1 WHERE id=#{id}")
    void transferhide(int id);

    @Select("select * from view_num_by_category")
    List<CategoryBean> selectDataOne();

}
