package com.zoe.wiki.service;

import com.zoe.wiki.domain.Demo;
import com.zoe.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){
        // 查表中所有数据
        return demoMapper.selectByExample(null);
    }
}
