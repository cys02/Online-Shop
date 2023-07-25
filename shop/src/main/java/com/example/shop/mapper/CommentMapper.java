package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CommentBean;
import org.apache.ibatis.annotations.Select;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface CommentMapper extends BaseMapper<CommentBean> {
    @Select("select * from view_comment")
    List<CommentBean> selectComment();

    @Select("select * from view_comment WHERE pid=#{pid}")
    List<CommentBean> selectCommentById(int pid);
}
