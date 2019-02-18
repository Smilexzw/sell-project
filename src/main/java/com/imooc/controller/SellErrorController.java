package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: xuzhangwang
 * @Description: 错误页面定制
 */
@Controller
public class SellErrorController implements ErrorController{


    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @Autowired
    public SellErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }


    /**
     * web页面的错误处理
     *
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        switch (status) {
            case 404:
                return "404";
            case 403:
                return "403";
            case 500:
                return "500";
        }
        return "buyer/buyer-index";
    }

}
