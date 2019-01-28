package com.example.gym2.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: zhangbo
 * @Date: 2019/1/25/025 14:34
 * @Version 1.0
 */

@SpringBootConfiguration
public class UrlWebAppConfigurer extends WebMvcConfigurationSupport {
    @Autowired
    UrlInterceptor urlInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
//        registry.addInterceptor(urlInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/register","/swagger**");
    }
}
