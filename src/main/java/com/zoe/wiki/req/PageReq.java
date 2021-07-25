package com.zoe.wiki.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Null;

public class PageReq {
    @Null(message = "page不能为空")
    private int page;

    @Null(message = "size不能为空")
    @Max(value = 1000, message = "size不能超过1000")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        // 不建议使用Lombok
        final StringBuffer sb = new StringBuffer("PageReq{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append("}");
        return sb.toString();
    }
}
