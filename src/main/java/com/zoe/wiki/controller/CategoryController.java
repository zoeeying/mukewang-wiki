package com.zoe.wiki.controller;

import com.zoe.wiki.req.CategoryQueryReq;
import com.zoe.wiki.req.CategorySaveReq;
import com.zoe.wiki.resp.CommonResp;
import com.zoe.wiki.resp.CategoryQueryResp;
import com.zoe.wiki.resp.PageResp;
import com.zoe.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> categoryList = categoryService.list(req);
        resp.setContent(categoryList);
        return resp;
    }

    /**
     * RequestBody注解表示接收json方式的POST提交
     * 如果请求的Content-Type是application/x-www-form-urlencoded，那么不需要加任何注解
     */
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        categoryService.delete(id);
        return resp;
    }
}
