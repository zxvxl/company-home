package com.hongyuan.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("cms_case")
public class CmsCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 分类ID */
    private Long categoryId;

    /** 项目名称 */
    private String title;

    /** 封面图 */
    private String coverImage;

    /** 图片列表(JSON数组) */
    private String images;

    /** 项目摘要 */
    private String summary;

    /** 项目详情(富文本) */
    private String content;

    /** 项目地点 */
    private String location;

    /** 工期 */
    private String projectDate;

    /** 工程规模 */
    private String projectScale;

    /** 状态: 0隐藏 1显示 */
    private Integer status;

    /** 是否首页推荐: 0否 1是 */
    private Integer isFeatured;

    /** 排序 */
    private Integer sortOrder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
