package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Data
public class OrderForm {

    /** 卖家姓名 */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号码
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "买家地址必填")
    private String address;

    /**
     * 买家的openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 买家的购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;


}
