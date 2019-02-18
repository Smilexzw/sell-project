package com.imooc.utils;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
public class KeyUtilTest {

    // 生成两位的随机数
    @Test
    public void getUniqueKey() throws Exception {
        Random random = new Random();
        // 随机生成一个整形的数
        Integer a = random.nextInt();
        Integer b = random.nextInt(100);
        System.out.println(a);
        System.out.println(b);

        // 或则使用Math的库中的随机数进行生成数字
        Integer c = new Double(Math.random() * 100).intValue();
        System.out.println(c);

        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void getKey() {
        Integer b = new Double(Math.random() * 10).intValue();
        Integer a = new Double(Math.random() * 1000000).intValue();
        System.out.println(a);
        System.out.println(b);
    }

}