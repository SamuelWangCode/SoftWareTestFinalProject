package com.eruka.searchSummonerservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {

    // 跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }


    // 跨域资源路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String baseURL = "D:\\SOA服务器";
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("file:" + baseURL);
    }
//    // 拦截器配置重定向有问题
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        //注册自己的拦截器,并设置拦截路径，拦截多个可以全一个list集合
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/api/login");
//        super.addInterceptors(registry);
//    }
}
