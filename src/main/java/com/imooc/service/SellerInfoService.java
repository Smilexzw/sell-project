package com.imooc.service;

import com.imooc.entity.SellerInfo;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
public interface SellerInfoService {

    public SellerInfo findByUsername(String username);

    public SellerInfo findByUsernameAndPassword(SellerInfo sellerInfo);

    /**
     * 根据卖家的openid进行查询SellerInfo的信息
     * @param openid
     * @return
     */
    public SellerInfo findByOpenid(String openid);

}
