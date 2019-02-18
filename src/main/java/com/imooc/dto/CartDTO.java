package com.imooc.dto;

import lombok.Data;

/**
 * @Auther: xuzhangwang
 * @Description: 购物车
 * 购物车中的信息：productId和productQuantity数量
 */
@Data
public class CartDTO {


    /** 商品id */
    private String productId;

    /** 数量 */
    private Integer  productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

}
