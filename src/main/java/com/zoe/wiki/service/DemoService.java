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

    // 查表中所有数据
    public List<Demo> list(){
         return demoMapper.selectByExample(null);
    }

    // 查表中单个数据
    public Demo demo(){
        return demoMapper.selectByPrimaryKey(Long.valueOf(1));
    }
}
