package com.zoe.wiki.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryQueryResp {
    private Long id;

    private Long parent;

    private String name;

    private Integer sort;
}