package com.imooc.repository;

import com.imooc.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{

    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);
}
