package com.zoe.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoe.wiki.domain.Category;
import com.zoe.wiki.domain.CategoryExample;
import com.zoe.wiki.mapper.CategoryMapper;
import com.zoe.wiki.req.CategoryQueryReq;
import com.zoe.wiki.req.CategorySaveReq;
import com.zoe.wiki.resp.CategoryQueryResp;
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
public class CategoryService {
    // 打印日志
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();

        // 只对下面第一个select语句起作用
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        // 通过pageInfo获取其它分页信息
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("总行数: {}", pageInfo.getTotal());
        LOG.info("总页数: {}", pageInfo.getPages());

        // List<Category>需要转换成List<CategoryQueryResp>，需要使用循环
        List<CategoryQueryResp> categoryRespList = new ArrayList<>();
        for(Category category : categoryList){
            CategoryQueryResp categoryResp = new CategoryQueryResp();
            // 把category中的每个属性拷贝到categoryResp中
            BeanUtils.copyProperties(category, categoryResp);
            categoryRespList.add(categoryResp);
        }
        // CopyUtil中封装了上面的循环拷贝操作
        // List<CategoryQueryResp> categoryRespList = CopyUtils.copyList(categoryList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(categoryRespList);
        return pageResp;
    }

    public void save(CategorySaveReq req){
        // 把请求参数转换成实体
        Category category = CopyUtils.copy(req, Category.class);
        if(ObjectUtils.isEmpty(req.getId())){
            // 新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        } else {
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    // 删除接口
    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }
}
