package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CollectBean;
import com.example.shop.bean.CommentBean;
import com.example.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectMapper extends BaseMapper<CollectBean> {
    @Select("select * from view_collect")
    List<CollectBean> selectCollect();

    @Select("select * from view_collect WHERE uid=#{uid} AND pid=#{pid}")
    List<CollectBean> selectCollectByUidPid(int uid,int pid);

    @Delete("delete from tbl_collect WHERE uid=#{uid} AND pid=#{pid}")
    void deleteByUidPid(int uid,int pid);

    @Select("select * from view_vxcollect WHERE bid=#{bid}")
    List<ProductBean> selectVxCollect(int bid);
}
