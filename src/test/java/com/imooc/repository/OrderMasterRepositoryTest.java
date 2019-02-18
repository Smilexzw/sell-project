package com.imooc.repository;

import com.imooc.entity.OrderMaster;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

     private final String OPENID = "100100";

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("大师兄");
        orderMaster.setBuyerPhone("18379368078");
        orderMaster.setBuyerAddress("米兰春天");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(19.8));


        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);

    }


    @Test
    public void findBuyerOpenid() throws Exception {
        // 分页设置，一页设置三条数目
        PageRequest request = new PageRequest(0, 3);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
        System.out.println(result.getTotalElements());
    }


}