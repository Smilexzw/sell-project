package com.imooc.enums;

import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;

/**
 * @Auther: xuzhangwang
 * @Description: 订单状态的枚举类
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0, "新订单"),
    FINISHED(1,"完结"),
    CANCEL(2, "已经取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
