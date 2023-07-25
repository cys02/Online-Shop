package com.example.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration // 配置注解，这个类里面有一些配置代码
@SpringBootApplication // 这个类标注成是一个SpringBoot项目启动类
@MapperScan("com.example.shop.mapper") // MyBatis管辖范围
public class ShopApplication implements WebMvcConfigurer {
    // bean    普通类----数据表
    // controller  SpringMvc 里面写网址----代码
    // mapper  MyBatis---SQL
    // util    所有工具放入此处

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        // 项目只有在启动成功后，就会出现一个笑脸
        System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
    
//    @Bean
//    public MybatisPlusInterceptor page() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        return interceptor;
//    }

    @Override // 虚拟路径：在上传图片成功后要用，可以将未显示的图片显示出来
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/shop/**").addResourceLocations("file:N:/cys/create/shop/");
    }
}
