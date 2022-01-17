package com.zoe.wiki.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class CategorySaveReq {
    private Long id;

    private Long parent;

    @NotNull(message = "name不能为空")
    private String name;

    @NotNull(message = "sort不能为空")
    private Integer sort;
}