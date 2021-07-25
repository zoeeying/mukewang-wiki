package com.zoe.wiki.controller;

import com.zoe.wiki.domain.Demo;
import com.zoe.wiki.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
// 接口前缀
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private DemoService demoService;

    @GetMapping("/list")
    public List<Demo> list(){
        return demoService.list();
    }

    @GetMapping("/one")
    public Demo demo(){
        return demoService.demo();
    }
}
