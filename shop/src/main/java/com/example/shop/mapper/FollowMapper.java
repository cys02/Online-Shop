package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.FollowBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FollowMapper extends BaseMapper<FollowBean> {
    @Select("select * from view_follow")
    List<FollowBean> selectFollow();

    @Select("select * from tbl_follow WHERE bid=#{bid} AND sid=#{sid}")
    List<FollowBean> selectFollowByBidSid(int bid,int sid);

    @Delete("delete from tbl_follow WHERE bid=#{bid} AND sid=#{sid}")
    void deleteByBidSid(int bid, int sid);

    @Select("select * from view_vxfollow WHERE bid=#{bid}")
    List<FollowBean> selectvxFollow(int bid);
}
