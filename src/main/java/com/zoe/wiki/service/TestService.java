package com.zoe.wiki.service;

import com.zoe.wiki.domain.Test;
import com.zoe.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


// @Service注解表示这个Service交给Spring来管理
// Spring会扫描到这个类
@Service
public class TestService {

    // @Resource注解会把TestMapper注入进来，也可以使用@Autowired注解
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }
}
