package com.imooc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: xuzhangwang
 * @Description: 产品分类
 * ON UPDATE CURRENT_TIMESTAMP 数据库中设置updateTime属性更新时间
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    /** 类目id */
    private Integer categoryId;

    /** 类目名字 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

    /** 类目创建时间 */
    private Date createTime;

    /** 类目更新时间 */
    private Date updateTime;


    public ProductCategory() {};

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
