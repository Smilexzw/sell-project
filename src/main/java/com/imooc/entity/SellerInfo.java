package com.imooc.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: xuzhangwang
 * @Description:
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {

    /** 卖家的id */
    @Id
    private String id;

    @NotNull(message = "请填写名称")
    /** 卖家的名称 */
    private String username;

    @NotNull(message = "请填写密码")
    /** 卖家的密码 */
    private String password;

    /** 卖家的openid */
    private String openid;

    /** 账号的创建时间 */
    private Date createTime;

    /** 账号的更新时间 */
    private Date updateTime;
}
