package com.imooc.repository;

import com.imooc.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    // 主键是的integer整数类型

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
