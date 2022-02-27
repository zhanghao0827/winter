package org.winter.common.enums;

public enum QueryType {

    //模糊查询 支持String
    LIKE,
    //关键字匹配 支持String
    EQUALS,
    /**
     * between and 支持 Long,Integer,Timestamp
     * BETWEEN区间值已逗号隔开
     * 如：
     * "2021-01-01,2020-01-20"
     * "1,10"
     */
    BETWEEN
}
