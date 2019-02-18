package com.imooc.controller;

import com.imooc.entity.SellerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Controller
public class SellerController {

    /**
     * 后台管理的主页面
     * @return
     */
    @GetMapping("/seller/center")
    public String adminCenterPage() {
        return "seller/center";
    }

    /**
     * 后后管理员欢迎界面
     * @return
     */
    @GetMapping("/seller/welcome")
    public String welcomePage() {
        return "seller/welcome";
    }

}
