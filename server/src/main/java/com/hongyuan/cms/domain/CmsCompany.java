package com.hongyuan.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("cms_company")
public class CmsCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 公司全称 */
    private String companyName;

    /** 公司简称 */
    private String shortName;

    /** Logo图片地址 */
    private String logoUrl;

    /** 公司简介 */
    private String description;

    /** 成立年份 */
    private Integer foundedYear;

    /** 公司地址 */
    private String address;

    /** 联系电话 */
    private String phone;

    /** 联系邮箱 */
    private String email;

    /** 传真 */
    private String fax;

    /** 邮编 */
    private String postalCode;

    /** 微信二维码图片地址 */
    private String wechatQrcode;

    /** 资质描述 */
    private String qualification;

    /** 企业文化(JSON: mission/vision/values) */
    private String culture;

    /** 发展历程(JSON数组) */
    private String history;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
