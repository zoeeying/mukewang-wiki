package com.zoe.wiki.service;

import com.zoe.wiki.domain.Ebook;
import com.zoe.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(){
        // 查表中所有数据
        return ebookMapper.selectByExample(null);
    }
}
