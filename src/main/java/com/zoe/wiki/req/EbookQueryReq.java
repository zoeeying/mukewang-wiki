package com.zoe.wiki.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EbookQueryReq extends PageReq {
    private Long id;

    private String name;
}