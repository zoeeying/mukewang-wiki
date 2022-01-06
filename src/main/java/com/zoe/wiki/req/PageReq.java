package com.zoe.wiki.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class PageReq {
    @NotNull(message = "page不能为空")
    private int page;

    @NotNull(message = "size不能为空")
    @Max(value = 1000, message = "size不能超过1000")
    private int size;
}
