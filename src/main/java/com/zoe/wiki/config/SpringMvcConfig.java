//package com.zoe.wiki.config;
//
//import com.zoe.wiki.interceptor.LogInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class SpringMvcConfig implements WebMvcConfigurer {
//
//    // 把拦截器注入进来
//    @Resource
//    LogInterceptor logInterceptor;
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 对于所有请求，除了/login，注册拦截器
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**").excludePathPatterns("/login");
//    }
//}
