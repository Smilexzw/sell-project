package com.imooc.entity;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: xuzhangwang
 * @Description: 用户下单
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /** 订单id */
    @Id
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号码 */
    private String buyerPhone;

    /** 买家的地址 */
    private String buyerAddress;

    /** 买家的微信openid */
    private String buyerOpenid;

    /** 订单的总数 */
    private BigDecimal orderAmount;

    /** 订单状态 0为默认的新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态， 默认0为未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();


    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;


}
