package com.zoe.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoe.wiki.domain.Ebook;
import com.zoe.wiki.domain.EbookExample;
import com.zoe.wiki.mapper.EbookMapper;
import com.zoe.wiki.req.EbookQueryReq;
import com.zoe.wiki.req.EbookSaveReq;
import com.zoe.wiki.resp.EbookQueryResp;
import com.zoe.wiki.resp.PageResp;
import com.zoe.wiki.utils.CopyUtils;
import com.zoe.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class EbookService {
    // 打印日志
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        // 动态SQL：如果传递了请求参数name，就按照name进行模糊查询，否则就返回全部数据
        if(!ObjectUtils.isEmpty(req.getName())) {
            // %表示左匹配、右匹配
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // 只对下面第一个select语句起作用
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        // 通过pageInfo获取其它分页信息
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

        // List<Ebook>需要转换成List<EbookQueryResp>，需要使用循环
        List<EbookQueryResp> ebookRespList = new ArrayList<>();
        for(Ebook ebook : ebookList){
            EbookQueryResp ebookResp = new EbookQueryResp();
            // 把ebook中的每个属性拷贝到ebookResp中
            BeanUtils.copyProperties(ebook, ebookResp);
            ebookRespList.add(ebookResp);
        }
        // CopyUtil中封装了上面的循环拷贝操作
        // List<EbookQueryResp> ebookRespList = CopyUtils.copyList(ebookList, EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ebookRespList);
        return pageResp;
    }

    // 保存接口
    public void save(EbookSaveReq req){
        // 把请求参数转换成实体
        Ebook ebook = CopyUtils.copy(req, Ebook.class);
        if(ObjectUtils.isEmpty(req.getId())){
            // 新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        } else {
            // 修改
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    // 删除接口
    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }
}
