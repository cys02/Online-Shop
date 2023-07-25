package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ProductMapper extends BaseMapper<ProductBean> {

    //从视图中找，按照时间倒序
    @Select("select * from v_product where uid=#{uid} order by ctime desc")
    List<ProductBean> selectList(@Param("uid")int uid);

    @Select("select * from v_product order by id desc")
    List<ProductBean> selectView();

    @Select("select * from v_product where hot='热卖'")
    List<ProductBean> selectHot();

    @Select("select * from v_product where cid=#{cid} order by id desc")
    List<ProductBean> selectProduct(@Param("cid")int cid);

    @Update("update tbl_product set hide=1 WHERE id=#{id}")
    void transferhide(int id);

    @Select("select * from tbl_product WHERE uid=1")
    List<ProductBean> selectBySid(int sid);
}
