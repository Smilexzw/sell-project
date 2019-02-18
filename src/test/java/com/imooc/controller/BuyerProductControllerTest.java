package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.entity.ProductCategory;
import com.imooc.entity.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BuyerProductControllerTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有的上架商品的信息
     * @throws Exception
     */
    @Test
    public void findupALL() throws Exception {


        List<ProductInfo> productInfoList =  productService.findUpAll();


        // 根据商品类目进行分类查询，一次性查询，使用普通的方法
//        List<Integer> categoryList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
//            categoryList.add(productInfo.getCategoryType());
            System.out.println(productInfo.toString());
        }

        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        for (Integer integer : categoryTypeList) {
            System.out.println("类目:" + integer);
        }

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        for (ProductCategory productCategory : productCategoryList) {
            System.out.println(productCategory.toString());
        }

        // 数据拼接，拼接程想要的json的格式
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            // json（name)
            productVO.setCategoryName(productCategory.getCategoryName());
            // json (type)
            productVO.setCategoryType(productCategory.getCategoryType());


            // 接下来拼foods
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 使用spring提供的工具，将一个对象直接从copy到另一个对象中
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);

            for (ProductInfoVO productInfoVO : productInfoVOList) {
                System.out.println(productInfoVO.toString());
            }


        }

    }

}