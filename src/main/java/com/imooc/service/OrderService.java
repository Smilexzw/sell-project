package com.imooc.service;

import com.imooc.dto.OrderDTO;
import com.imooc.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description: 订单业务接口
 * 订单可以对应多条商品的信息
 */
public interface OrderService {

    /** 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单 */
    OrderDTO findOne(String orderId);

    /** 查询订单列表, 根据的是用户的openid 进行查询订单 */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完成订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);


    /** 查询所有的订单 */
    Page<OrderDTO> findAllOrderList(Pageable pageable);

}
