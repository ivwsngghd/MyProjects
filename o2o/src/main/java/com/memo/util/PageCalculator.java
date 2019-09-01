package com.memo.util;

public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
        //返回选取第几页转化为第几条数据的位置
    }
}
