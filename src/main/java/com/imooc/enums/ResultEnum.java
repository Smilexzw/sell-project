package com.imooc.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(11, "商品库存不足"),

    ORDER_NOT_EXIST(404, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(404, "详细订单不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单信息为空"),

    ORDER_PAY_STAATUS_ERROER(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为空"),


    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    ORDER_FINSH_SUCCESS(22, "订单完结成功"),

    PRODUCT_STATUS_ERROR(23, "商品状态不正确"),

    SELLER_NOT_EXIST(50000, "用户不存在"),

    SELLER_PASSWORD_ERROR(50001, "用户密码错误"),

    SELLER_NOT_NULL(50002, "用户信息不能为空");



    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
