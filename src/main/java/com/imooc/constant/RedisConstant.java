package com.imooc.constant;

/**
 * @Auther: xuzhangwang
 * @Description: redis的常量
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    // 设置redis的过期时间为两个小时
    Integer EXPIRE = 7200;


}
