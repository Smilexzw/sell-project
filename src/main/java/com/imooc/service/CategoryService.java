package com.imooc.service;

import com.imooc.entity.ProductCategory;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description: 类目
 */
public interface CategoryService {

    /** 根据类目的Id进行查询的 */
    ProductCategory findOne(Integer categoeyId);

    /** 查询的所有的类目 */
    List<ProductCategory> findAll();

    /** 根据类目的类型进行查询 */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

    /** 类目的数据的保存，更新等等操作 */
    ProductCategory save(ProductCategory productCategory);
}
