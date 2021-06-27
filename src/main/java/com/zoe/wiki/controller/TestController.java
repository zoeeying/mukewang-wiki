package com.zoe.wiki.controller;

import com.zoe.wiki.domain.Test;
import com.zoe.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    // 读取自定义配置项，并赋值给testHello
    @Value("${test.hello:ZOE}")
    private String testHello;

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String hello(){
        return "GET: Hello World";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "POST: Hello " + name;
    }

    @GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
}
