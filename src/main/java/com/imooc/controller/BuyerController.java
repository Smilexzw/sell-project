package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Controller
public class BuyerController {

    @GetMapping(value = {"/buyer/index", "/buyer"})
    public String buyerIndexPage() {
        return "buyer/buyer-index";
    }

}
