package com.imooc.repository;

import com.imooc.entity.SellerInfo;
import com.imooc.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {

    @Autowired
    SellerInfoRepository repository;

    @Test
    public void saveTet() {
        // 测试注册卖家用户的信息的
        SellerInfo sellInfo = new SellerInfo();
        sellInfo.setId(KeyUtil.getUniqueKey());
        sellInfo.setUsername("米兰春天");
        sellInfo.setPassword("1234567");
        sellInfo.setOpenid(KeyUtil.getUniqueKey());
        repository.save(sellInfo);
    }

    @Test
    public void findOne() {
        SellerInfo sellerInfo = repository.findByUsername("米兰春天");
        System.out.println(sellerInfo.toString());
        Assert.assertNotNull(sellerInfo);

    }

    @Test
    public void findByOpenid() {
        SellerInfo byOpenid = repository.findByOpenid("1548662420297709625");
        Assert.assertNotNull(byOpenid);
    }

}