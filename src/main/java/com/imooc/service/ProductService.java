package com.imooc.service;

import com.imooc.dto.CartDTO;
import com.imooc.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description: 商品
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有的在架商品列表
     */
    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);

    // 加库存
    void insertStock(List<CartDTO> cartDTOList);

    // 减库存
    void deleteStock(List<CartDTO> cartDTOList);

    // 商品上架
    ProductInfo onSale(String productId);

    // 商品下架
    ProductInfo offSale(String productId);

    // 删除商品
    void delProduct(String productId);


}
