package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.entity.OrderDetail;
import com.imooc.entity.OrderMaster;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description: dto 用于在各个层进行传输
 */
@Data
public class OrderDTO {

    /** 订单id */
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

    @Transient
    /** 查询订单列表 */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }



}
