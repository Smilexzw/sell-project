package com.imooc.service.impl;

import com.imooc.entity.SellerInfo;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {

    private static final String openid = "1548662420297709625";

    @Autowired
    SellerInfoService sellerInfoService;



    @Test
    public void findByUsername() throws Exception {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername("米兰春天");

        SellerInfo result = sellerInfoService.findByUsername(sellerInfo.getUsername());
        System.out.println(result.toString());
        Assert.assertNotEquals(sellerInfo, result);
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setUsername("米兰春天");
        sellerInfo.setPassword("1234567");
        sellerInfoService.findByUsernameAndPassword(sellerInfo);
    }

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo result = sellerInfoService.findByOpenid(openid);
        System.out.println(result.toString());
    }

}