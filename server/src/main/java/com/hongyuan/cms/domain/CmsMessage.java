package com.hongyuan.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("cms_message")
public class CmsMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 姓名 */
    private String name;

    /** 电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 公司名称 */
    private String company;

    /** 主题 */
    private String subject;

    /** 留言内容 */
    private String content;

    /** 是否已读: 0未读 1已读 */
    private Integer isRead;

    /** 回复内容 */
    private String replyContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
