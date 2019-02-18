package com.imooc.repository;

import com.imooc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

    List<OrderDetail> findByOrderId(String orderId);
}
