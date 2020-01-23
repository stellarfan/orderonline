package com.chao.bysj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: wangchao
 * @Date: 2019/3/12 10:53
 * @Description:
 */
@Configuration
public class MyWebAppConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
         *这是图片的物理路径  "file:/+本地图片的地址"
         * @Date： Create in 14:08 2017/12/20
         */     registry.addResourceHandler("/product/**").addResourceLocations("file:/F:/olderonline/src/main/resources/static/");
        super.addResourceHandlers(registry);
    }
}
