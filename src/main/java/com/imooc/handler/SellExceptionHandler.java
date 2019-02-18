package com.imooc.handler;

import com.imooc.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: xuzhangwang
 * @Description: 对全局的异常进行捕捉
 */
@ControllerAdvice
public class SellExceptionHandler {


    // 拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("/seller/login");
    }

}
