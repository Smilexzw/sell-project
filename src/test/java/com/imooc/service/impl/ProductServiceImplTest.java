package com.imooc.service.impl;

import com.imooc.entity.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() throws Exception {
        PageRequest result = new PageRequest(0,5);
        Page<ProductInfo> productPageInfo = productService.findAll(result);
        System.out.println(productPageInfo.getTotalElements());
        System.out.println(productPageInfo.getTotalPages());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        System.out.println(list.toString());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(KeyUtil.getUniqueKey());
        productInfo.setProductName("灌汤包");
        productInfo.setProductPrice(new BigDecimal(8.0));
        productInfo.setProductStock(20);
        productInfo.setProductIcon("http://xxxx1.jpg");
        productInfo.setProductDescription("肉质新鲜，不油腻的，好吃不上火!!!");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        // 保存商品的信息
        productService.save(productInfo);
    }

    /**
     * 测试商品的上架
     */
    @Test
    public void onSale() {
        ProductInfo productInfo = productService.onSale("1549504295440");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(), productInfo.getProductStatus());
    }

    /**
     * 测试商品的下架
     */
    @Test
    public void offSale() {
        ProductInfo productInfo = productService.offSale("1549504295440");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(), productInfo.getProductStatus());
    }


    /**
     *
     * 测试商品的删除
     */
    @Test
    public void delProduct() {

        productService.delProduct("154960833788387279");
    }

}