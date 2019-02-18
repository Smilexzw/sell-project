package com.imooc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: xuzhangwang
 * @Description: 订单信息
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    /** 订单id */
    @Id
    private String detailId;

    /** 用户下单Id */
    private String orderId;

    /** 商品Id */
    private String productId;

    /** 商品名字 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品数量 */
    private Integer productQuantity;

    /** 商品icon */
    private String productIcon;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

}
