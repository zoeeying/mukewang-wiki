//package com.zoe.wiki.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//// 添加@Component注解，这样Spring Boot就会自动去扫描LogFilter类，容器就会拿到这个过滤器了
//@Component
//public class LogFilter implements Filter {
//
//    private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        // 打印请求信息
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        LOG.info("------------- LogFilter开始 -------------");
//        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
//        LOG.info("远程地址: {}", request.getRemoteAddr());
//
//        // 记录开始时间
//        long startTime = System.currentTimeMillis();
//
//        // 执行后面的过滤器，如果后面没有过滤器，就执行业务代码
//        filterChain.doFilter(servletRequest, servletResponse);
//
//        LOG.info("------------- LogFilter结束耗时: {} ms -------------", System.currentTimeMillis() - startTime);
//    }
//}