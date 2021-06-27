package com.zoe.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    // 读取自定义配置项，并赋值给testHello
    @Value("${test.hello:ZOE}")
    private String testHello;

    @GetMapping("/hello")
    public String hello(){
        return "GET: Hello World";
    }


    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "POST: Hello " + name;
    }
}
