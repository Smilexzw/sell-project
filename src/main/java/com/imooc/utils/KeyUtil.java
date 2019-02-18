package com.imooc.utils;

import java.util.Random;

/**
 * @Auther: xuzhangwang
 * @Description: 生成唯一主键
 */
public class KeyUtil {

    // 格式 时间 + 随机数目
    public static synchronized String getUniqueKey() {
        Integer key = new Double(Math.random() * 100000).intValue();
        return System.currentTimeMillis() + String.valueOf(key);
    }
}
