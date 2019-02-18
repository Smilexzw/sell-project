package com.imooc.enums;

import lombok.Getter;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1, "支付成功"),
    FAIL(2, "支付失败");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
