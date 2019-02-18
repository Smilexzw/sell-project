package com.imooc.repository;

import com.imooc.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    public List<ProductInfo> findByProductStatus(Integer productStatus);

}
