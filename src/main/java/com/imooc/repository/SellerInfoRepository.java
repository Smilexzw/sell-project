package com.imooc.repository;

import com.imooc.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: xuzhangwang
 * @Description: 卖家数据处理
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String>{


    public SellerInfo findByUsername(String username);

    public SellerInfo findByUsernameAndPassword(String username, String password);

    public SellerInfo findByOpenid(String openid);


}
