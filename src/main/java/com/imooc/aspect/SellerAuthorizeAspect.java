package com.imooc.aspect;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {


    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 定义切入点，验证Cookie中是否有信息，如果没有信息就做业务处理的,取了 login 和 logout
     */
    @Pointcut("execution(public * com.imooc.controller.Seller*.*(..))" +
    "&& !execution(public * com.imooc.controller.SellerInfoController.*(..))")
    public void verify() {}


    /**
     * 在切入点之前
     */
    @Before("verify()")
    public void doverify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登陆校验】Cookie中国查询不到token");
            throw new SellerAuthorizeException();
        }

        // 去redis中查询
        String tokenVAlue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenVAlue)) {
            log.warn("【登陆校验】redis中查询得不到token");
            throw new SellerAuthorizeException();
        }


    }

}
