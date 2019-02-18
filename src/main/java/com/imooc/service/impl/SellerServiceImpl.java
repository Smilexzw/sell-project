package com.imooc.service.impl;

import com.imooc.entity.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Slf4j
@Service
public class SellerServiceImpl implements SellerInfoService {

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findByUsername(String username) {
        return sellerInfoRepository.findByUsername(username);
    }

    @Override
    public SellerInfo findByUsernameAndPassword(SellerInfo sellerInfo) {
        if (sellerInfo.getUsername().equals("") || sellerInfo.getPassword().equals("")) {
            throw new SellException(ResultEnum.SELLER_NOT_NULL);
        }
        if (sellerInfoRepository.findByUsername(sellerInfo.getUsername()) == null) {
            log.error("【用户登入异常】 用户不存在 sellerIfo={}" , sellerInfo);
            throw new SellException(ResultEnum.SELLER_NOT_EXIST);
        }
        SellerInfo result = sellerInfoRepository.findByUsernameAndPassword(sellerInfo.getUsername(), sellerInfo.getPassword());
        if (result == null || !result.getPassword().equals(sellerInfo.getPassword())) {
            log.error("【用户登入异常】 用户错误的 sellerIfo={}" , sellerInfo);
            throw new SellException(ResultEnum.SELLER_PASSWORD_ERROR);
        }
        return result;
    }

    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
