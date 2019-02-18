package com.imooc.service.impl;

import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dto.OrderDTO;
import com.imooc.entity.OrderDetail;
import com.imooc.entity.OrderMaster;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    private final String BUYER_OPENID = "100101";

    private final String OrderID = "1548838343710446989";

    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("徐章旺");
        orderDTO.setBuyerAddress("江南商贸城米兰春天76栋1单元1503");
        orderDTO.setBuyerPhone("18379368078");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(3);
        orderDetailList.add(o2);

        OrderDetail o3 = new OrderDetail();
        o3.setProductId("123455");
        o3.setProductQuantity(2);
        orderDetailList.add(o3);

        OrderDetail o4 = new OrderDetail();
        o4.setProductId("123458");
        o4.setProductQuantity(1);
        orderDetailList.add(o4);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);

    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne(OrderID);
        System.out.println(orderDTO);
    }

    @Test
    public void findList() throws Exception {

    }

    @Test
    public void cancel() throws Exception {

        OrderDTO orderDTO = orderService.findOne(OrderID);
        OrderDTO result =  orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    //
    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne(OrderID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());

    }

    @Test
    public void paid() throws Exception {

        OrderDTO orderDTO = orderService.findOne(OrderID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void findAllOrderList() {
        // 查询第0页, 前面两个数据
        PageRequest request = new PageRequest(1, 2);
        Page<OrderDTO> orderDTOPage = orderService.findAllOrderList(request);
//        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("查询偶有的订单列表", orderDTOPage.getTotalElements() > 0);
    }

}