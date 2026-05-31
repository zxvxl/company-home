package com.hongyuan.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("cms_partner")
public class CmsPartner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 伙伴名称 */
    private String name;

    /** Logo地址 */
    private String logoUrl;

    /** 官网链接 */
    private String websiteUrl;

    /** 排序 */
    private Integer sortOrder;

    /** 状态: 0停用 1启用 */
    private Integer status;
}
