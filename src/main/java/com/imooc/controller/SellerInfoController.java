package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.entity.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.SellerInfoService;
import com.imooc.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Controller
public class SellerInfoController {

    @Autowired
    SellerInfoService sellerInfoService;

    @Autowired
    StringRedisTemplate redisTemplate;


    /**
     * 卖家端登入验证页面
     * @return
     */
    @GetMapping("/seller/login")
    public String sellerLogin() {
        return "seller/login";
    }


    @ResponseBody
    @PostMapping("/seller/doLogin")
    public Map<String, Object> doLogin(@Validated SellerInfo sellerInfo, BindingResult bindingResult,
                                       HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            map.put("result", bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        try {
            SellerInfo result = sellerInfoService.findByUsernameAndPassword(sellerInfo);
            String token = UUID.randomUUID().toString().replace("-", "");
            Integer exprie = RedisConstant.EXPIRE;
            // 把token设置到reids中
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), result.getOpenid(), exprie, TimeUnit.SECONDS);

            // 3、设置cookie进入到cookie中
            CookieUtil.set(response, CookieConstant.TOKEN , token, exprie);
            map.put("result", "登入成功");
        } catch (SellException e) {
            map.put("result", e.getMessage());
        }
        return map;
    }



    @GetMapping("/seller/dologin")
    public ModelAndView dologin(@RequestParam("openid") String openid,
                                HttpServletResponse response,
                                Map<String, Object> map) {
        SellerInfo result = sellerInfoService.findByOpenid(openid);
        if (result == null) {
            // 查询不到信息
            map.put("msg", "查询不到信息");
            return new ModelAndView("seller/login");
        }
        // 设置token到redis中, token用UUID
        String token = UUID.randomUUID().toString().replace("-", "");
        Integer exprie = RedisConstant.EXPIRE;
        // 把token设置到reids中
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, exprie, TimeUnit.SECONDS);

        // 3、设置cookie进入到cookie中
        CookieUtil.set(response, CookieConstant.TOKEN , token, exprie);


        return new ModelAndView("seller/center");
    }

    @GetMapping("/seller/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        // 1、从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            // 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            // 3、清除cookie, 直接设置过期时间为0
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        return new ModelAndView("/seller/login");


    }

}
