-- ============================================================
-- 虹远水利工程有限公司 官网 CMS 业务表
-- 数据库: MySQL 8.0+
-- 字符集: utf8mb4
-- ============================================================

CREATE DATABASE IF NOT EXISTS `company_cms` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `company_cms`;

-- -----------------------------------------------------------
-- 1. 公司信息表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_company`;
CREATE TABLE `cms_company` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(200) NOT NULL COMMENT '公司全称',
  `short_name` varchar(100) DEFAULT NULL COMMENT '公司简称',
  `logo_url` varchar(500) DEFAULT NULL COMMENT 'Logo图片地址',
  `description` text COMMENT '公司简介',
  `founded_year` int DEFAULT NULL COMMENT '成立年份',
  `address` varchar(500) DEFAULT NULL COMMENT '公司地址',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '联系邮箱',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `postal_code` varchar(20) DEFAULT NULL COMMENT '邮编',
  `wechat_qrcode` varchar(500) DEFAULT NULL COMMENT '微信二维码图片地址',
  `qualification` text COMMENT '资质描述',
  `culture` text COMMENT '企业文化(JSON: mission/vision/values)',
  `history` text COMMENT '发展历程(JSON数组)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司信息表';

-- -----------------------------------------------------------
-- 2. 轮播图表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `subtitle` varchar(500) DEFAULT NULL COMMENT '副标题',
  `image_url` varchar(500) NOT NULL COMMENT '图片地址',
  `link_url` varchar(500) DEFAULT NULL COMMENT '跳转链接',
  `sort_order` int DEFAULT 0 COMMENT '排序(越小越前)',
  `status` tinyint DEFAULT 1 COMMENT '状态: 0停用 1启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_status_sort` (`status`, `sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图表';

-- -----------------------------------------------------------
-- 3. 业务领域表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_business`;
CREATE TABLE `cms_business` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) NOT NULL COMMENT '业务名称',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标(class或URL)',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图',
  `summary` varchar(1000) DEFAULT NULL COMMENT '简要描述',
  `content` text COMMENT '详细描述(富文本)',
  `sort_order` int DEFAULT 0 COMMENT '排序',
  `status` tinyint DEFAULT 1 COMMENT '状态: 0停用 1启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_status_sort` (`status`, `sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务领域表';

-- -----------------------------------------------------------
-- 4. 工程案例分类表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_case_category`;
CREATE TABLE `cms_case_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `sort_order` int DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程案例分类表';

-- -----------------------------------------------------------
-- 5. 工程案例表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_case`;
CREATE TABLE `cms_case` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `title` varchar(200) NOT NULL COMMENT '项目名称',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图',
  `images` text COMMENT '图片列表(JSON数组)',
  `summary` varchar(1000) DEFAULT NULL COMMENT '项目摘要',
  `content` text COMMENT '项目详情(富文本)',
  `location` varchar(200) DEFAULT NULL COMMENT '项目地点',
  `project_date` varchar(50) DEFAULT NULL COMMENT '工期',
  `project_scale` varchar(200) DEFAULT NULL COMMENT '工程规模',
  `status` tinyint DEFAULT 1 COMMENT '状态: 0隐藏 1显示',
  `is_featured` tinyint DEFAULT 0 COMMENT '是否首页推荐: 0否 1是',
  `sort_order` int DEFAULT 0 COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category_id`),
  KEY `idx_featured` (`is_featured`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程案例表';

-- -----------------------------------------------------------
-- 6. 新闻分类表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_news_category`;
CREATE TABLE `cms_news_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `sort_order` int DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻分类表';

-- -----------------------------------------------------------
-- 7. 新闻动态表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_news`;
CREATE TABLE `cms_news` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图',
  `summary` varchar(500) DEFAULT NULL COMMENT '摘要',
  `content` text COMMENT '正文(富文本)',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `view_count` int DEFAULT 0 COMMENT '浏览量',
  `is_top` tinyint DEFAULT 0 COMMENT '是否置顶: 0否 1是',
  `status` tinyint DEFAULT 0 COMMENT '状态: 0草稿 1已发布',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category_id`),
  KEY `idx_status_publish` (`status`, `publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻动态表';

-- -----------------------------------------------------------
-- 8. 合作伙伴表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_partner`;
CREATE TABLE `cms_partner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '伙伴名称',
  `logo_url` varchar(500) DEFAULT NULL COMMENT 'Logo地址',
  `website_url` varchar(500) DEFAULT NULL COMMENT '官网链接',
  `sort_order` int DEFAULT 0 COMMENT '排序',
  `status` tinyint DEFAULT 1 COMMENT '状态: 0停用 1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='合作伙伴表';

-- -----------------------------------------------------------
-- 9. 客户留言表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_message`;
CREATE TABLE `cms_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `company` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `subject` varchar(200) DEFAULT NULL COMMENT '主题',
  `content` text COMMENT '留言内容',
  `is_read` tinyint DEFAULT 0 COMMENT '是否已读: 0未读 1已读',
  `reply_content` text COMMENT '回复内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`),
  KEY `idx_is_read` (`is_read`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户留言表';

-- -----------------------------------------------------------
-- 10. 站点配置表
-- -----------------------------------------------------------
DROP TABLE IF EXISTS `cms_config`;
CREATE TABLE `cms_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `config_key` varchar(100) NOT NULL COMMENT '配置键',
  `config_value` text COMMENT '配置值',
  `config_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_config_key` (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点配置表';
