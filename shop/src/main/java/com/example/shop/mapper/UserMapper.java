package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// ibatis就是MyBatis前身
// ibatis-->mybatis-->mybatisplus
// Mapper是MyBatis区域，就是写SQL语句的地方
// 数据表-->Mapper-->Controller-->网页
// Spring容器，注入容器中@Autowired
@Repository // @Mapper   UserMapper应该是Spring容器中的组件
public interface UserMapper extends BaseMapper<UserBean> {
    // SQL语句非常重要，一定要熟悉
    // python 数据分析 玩到最后，就会发现跟SQL语句很像

    // 接口特点：1. 里面的方法都是抽象的
    //  2. 不用写访问控制符，默认public      #{} 取值
    @Select("select * from tbl_user where username=#{username} and password=#{password} and status=#{status}")
    UserBean getUser(UserBean bean);    //
    // 编译时，编译器会偷偷的把形参的名字改了
    @Select("select * from tbl_user where username=#{username} and password=#{password} and status='买家'")
    UserBean getBuyer(UserBean bean);
    @Select("select * from tbl_user where username=#{username}")
    UserBean haveUser(@Param("username")String username);

    @Select("select * from tbl_user where  status='卖家'")
    List<UserBean> getSalers();


}
