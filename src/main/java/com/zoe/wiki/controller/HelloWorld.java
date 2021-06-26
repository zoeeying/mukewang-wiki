package com.zoe.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public String hello(){
        return "GET: Hello World";
    }


    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "POST: Hello " + name;
    }
}
