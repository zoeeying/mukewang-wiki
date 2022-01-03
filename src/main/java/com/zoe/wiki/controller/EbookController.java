package com.zoe.wiki.controller;

import com.zoe.wiki.req.EbookQueryReq;
import com.zoe.wiki.req.EbookSaveReq;
import com.zoe.wiki.resp.CommonResp;
import com.zoe.wiki.resp.EbookQueryResp;
import com.zoe.wiki.resp.PageResp;
import com.zoe.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> ebookList = ebookService.list(req);
        resp.setContent(ebookList);
        return resp;
    }

    /**
     * RequestBody注解表示接收json方式的POST提交
     * 如果请求的Content-Type是application/x-www-form-urlencoded，那么不需要加任何注解
     */
    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        ebookService.delete(id);
        return resp;
    }
}
