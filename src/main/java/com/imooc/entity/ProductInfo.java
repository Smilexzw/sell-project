package com.imooc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: xuzhangwang
 * @Description: 商品信息详情表
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 单价 */
    private BigDecimal productPrice;

    /** 库存 **/
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 图标 */
    private String productIcon;

    /** 状态 0正常，1下架， 数据库中默认的是0
     *  直接获取productInfo的枚举类的code状态码
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 商品编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
