package com.imooc.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */

public class MD5Test {


    /**
     * 判断用户密码是否正确
     * @throws Exception
     */
    @Test
    public void encoderByMd5() throws Exception {
        String oldstr = "admin123z";
        String newstr = MD5.encoderByMd5(oldstr);
        System.out.println(newstr);

        if (newstr.equals(oldstr)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }

}