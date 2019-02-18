package com.imooc.VO;

import lombok.Data;

import java.util.List;

/**
 * http请求返回的最外层的对象
 * @Auther: xuzhangwang
 * @Description: 商品对象包装类返回给前端进行解析
 */
@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;
}
