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
import com.zoe.wiki.utils.CopyUtil;
import com.zoe.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
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
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // 只对下面第一个select语句起作用
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

        // List<Ebook>需要转换成List<EbookResp>，再返回controller，需要使用循环
        // 具体的实现细节封装在了CopyUtil中
        List<EbookQueryResp> ebookRespList = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ebookRespList);
        return pageResp;
    }

    // 保存接口
    public void save(EbookSaveReq req){
        // 把请求参数转换成实体
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
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
