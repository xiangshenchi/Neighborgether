package com.axy.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //分页查询默认参数
    private static int PAGE_SIZE = 10;
    private static int PAGE_NUM = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    private HashMap param;
}