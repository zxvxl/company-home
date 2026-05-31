# 虹远水利工程有限公司 官网项目 - 技术方案

## 一、项目概述

为虹远水利工程有限公司搭建企业官方网站，包含面向访客的展示前台和面向管理员的内容管理后台。

- **公司类型**：水利工程（水库大坝、河道治理、农田灌溉、防洪排涝、水土保持、供水工程）
- **技术路线**：RuoYi-Vue 二开（Spring Boot + Vue3 + MySQL）
- **核心原则**：数据全部存 MySQL、后台可交付客户使用、前台是正规企业官网样式

---

## 二、系统架构

```
                    ┌─────────────────────────────────────────────────┐
                    │                   Nginx 反向代理                  │
                    └────────────┬────────────────────┬───────────────┘
                                 │                    │
                    ┌────────────▼──────┐   ┌────────▼──────────────┐
                    │  官网前台 (Nuxt3)  │   │  管理后台 (Vue3+ElemPlus)│
                    │  端口: 3000       │   │  端口: 80              │
                    └────────────┬──────┘   └────────┬──────────────┘
                                 │                    │
                                 │   REST API         │
                                 └─────────┬──────────┘
                                           │
                              ┌─────────────▼──────────────┐
                              │   后端 API (Spring Boot 3)   │
                              │   端口: 8080                 │
                              │   ┌──────────────────────┐  │
                              │   │ RuoYi 通用模块        │  │
                              │   │ - 用户/角色/权限      │  │
                              │   │ - 菜单管理           │  │
                              │   │ - 操作日志           │  │
                              │   │ - 代码生成器         │  │
                              │   └──────────────────────┘  │
                              │   ┌──────────────────────┐  │
                              │   │ CMS 业务模块         │  │
                              │   │ - 轮播图管理         │  │
                              │   │ - 公司信息管理       │  │
                              │   │ - 业务领域管理       │  │
                              │   │ - 工程案例管理       │  │
                              │   │ - 新闻动态管理       │  │
                              │   │ - 合作伙伴管理       │  │
                              │   │ - 留言管理          │  │
                              │   └──────────────────────┘  │
                              └─────────────┬──────────────┘
                                            │
                              ┌─────────────▼──────────────┐
                              │     MySQL 8.0（外部服务）     │
                              │     端口: 3306              │
                              └────────────────────────────┘
```

---

## 三、技术栈明细

| 层次 | 技术 | 版本 | 说明 |
|------|------|------|------|
| 后端框架 | Spring Boot | 3.x | RuoYi-Vue 为底座 |
| ORM | MyBatis-Plus | 3.5+ | 简化 CRUD |
| 权限 | Spring Security + JWT | — | 后台登录鉴权 |
| 缓存 | Redis | 7.x | Session、验证码、热点数据 |
| 数据库 | MySQL | 8.0 | 外部独立服务 |
| 后台前端 | Vue 3 + Element Plus | — | RuoYi 自带后台 UI |
| 官网前台 | Nuxt 3 (Vue 3 SSR) | 3.x | SEO 友好、响应式 |
| 部署 | Docker + Docker Compose | — | MySQL + Redis + 后端 + 前台 |

---

## 四、数据库设计（CMS 业务表）

> 以下为 CMS 新增的业务表，RuoYi 自带的系统表（sys_user、sys_role 等）不列出。

### 4.1 公司信息表 `cms_company`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| company_name | varchar(200) | 公司名称 |
| short_name | varchar(100) | 简称 |
| logo_url | varchar(500) | Logo 图片地址 |
| description | text | 公司简介 |
| founded_year | int | 成立年份 |
| address | varchar(500) | 公司地址 |
| phone | varchar(50) | 联系电话 |
| email | varchar(100) | 联系邮箱 |
| fax | varchar(50) | 传真 |
| postal_code | varchar(20) | 邮编 |
| wechat_qrcode | varchar(500) | 微信二维码 |
| qualification | text | 资质描述 |
| culture | text | 企业文化（JSON格式：使命/愿景/价值观） |
| history | text | 发展历程（JSON数组） |
| create_time | datetime | 创建时间 |
| update_time | datetime | 更新时间 |

### 4.2 轮播图表 `cms_banner`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| title | varchar(200) | 标题 |
| subtitle | varchar(500) | 副标题 |
| image_url | varchar(500) | 图片地址 |
| link_url | varchar(500) | 跳转链接 |
| sort_order | int | 排序（越小越前） |
| status | tinyint | 状态：0停用 1启用 |
| create_time | datetime | 创建时间 |

### 4.3 业务领域表 `cms_business`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| title | varchar(200) | 业务名称 |
| icon | varchar(100) | 图标（class 或 URL） |
| cover_image | varchar(500) | 封面图 |
| summary | varchar(1000) | 简要描述 |
| content | text | 详细描述（富文本） |
| sort_order | int | 排序 |
| status | tinyint | 状态 |
| create_time | datetime | 创建时间 |
| update_time | datetime | 更新时间 |

### 4.4 工程案例分类表 `cms_case_category`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| name | varchar(100) | 分类名称 |
| sort_order | int | 排序 |

### 4.5 工程案例表 `cms_case`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| category_id | bigint FK | 分类 ID |
| title | varchar(200) | 项目名称 |
| cover_image | varchar(500) | 封面图 |
| images | text | 图片列表（JSON数组） |
| summary | varchar(1000) | 项目摘要 |
| content | text | 项目详情（富文本） |
| location | varchar(200) | 项目地点 |
| project_date | varchar(50) | 工期 |
| project_scale | varchar(200) | 工程规模 |
| status | tinyint | 状态 |
| is_featured | tinyint | 是否首页推荐 |
| sort_order | int | 排序 |
| create_time | datetime | 创建时间 |

### 4.6 新闻分类表 `cms_news_category`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| name | varchar(100) | 分类名称（如：公司动态、行业新闻、媒体报道） |
| sort_order | int | 排序 |

### 4.7 新闻动态表 `cms_news`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| category_id | bigint FK | 分类 ID |
| title | varchar(200) | 标题 |
| cover_image | varchar(500) | 封面图 |
| summary | varchar(500) | 摘要 |
| content | text | 正文（富文本） |
| author | varchar(50) | 作者 |
| view_count | int | 浏览量 |
| is_top | tinyint | 是否置顶 |
| status | tinyint | 状态：0草稿 1已发布 |
| publish_time | datetime | 发布时间 |
| create_time | datetime | 创建时间 |
| update_time | datetime | 更新时间 |

### 4.8 合作伙伴表 `cms_partner`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| name | varchar(200) | 伙伴名称 |
| logo_url | varchar(500) | Logo 地址 |
| website_url | varchar(500) | 官网链接 |
| sort_order | int | 排序 |
| status | tinyint | 状态 |

### 4.9 客户留言表 `cms_message`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| name | varchar(100) | 姓名 |
| phone | varchar(50) | 电话 |
| email | varchar(100) | 邮箱 |
| company | varchar(200) | 公司名称 |
| subject | varchar(200) | 主题 |
| content | text | 留言内容 |
| is_read | tinyint | 是否已读 |
| reply_content | text | 回复内容 |
| create_time | datetime | 提交时间 |

### 4.10 站点配置表 `cms_config`

| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint PK | 主键 |
| config_key | varchar(100) UNI | 配置键 |
| config_value | text | 配置值 |
| config_desc | varchar(200) | 描述 |
| update_time | datetime | 更新时间 |

> 用于存放：SEO 关键词、备案号、统计代码、首页数字展示（完工项目数/服务年限/团队规模/合作客户数）等。

---

## 五、官网前台页面设计

### 5.1 页面列表

| 页面 | 路由 | 数据来源 |
|------|------|---------|
| 首页 | / | banner + company + business + case(featured) + news(latest) + partner + config(stats) |
| 关于我们 | /about | company（简介、历程、文化、资质） |
| 业务领域 | /business | cms_business 列表 |
| 业务详情 | /business/:id | cms_business 单条 |
| 工程案例 | /cases | cms_case 列表 + 分类筛选 |
| 案例详情 | /cases/:id | cms_case 单条 |
| 新闻中心 | /news | cms_news 列表 + 分类筛选 + 分页 |
| 新闻详情 | /news/:id | cms_news 单条 |
| 联系我们 | /contact | company（地址/电话） + 留言表单提交 |

### 5.2 设计风格

- **主色**：#1a5276（深蓝 - 水利工程专业感）
- **辅色**：#2980b9（亮蓝 - 与水相关）
- **强调色**：#e67e22（工程橙 - 安全/施工）
- **风格**：大气、专业、稳重
- **响应式**：桌面 / 平板 / 手机三端适配

---

## 六、后台管理菜单

```
系统管理（RuoYi自带）
├── 用户管理
├── 角色管理
├── 菜单管理
├── 操作日志
└── 系统配置

内容管理（CMS 新增）
├── 轮播图管理      → 增删改查、拖拽排序、启用/停用
├── 公司信息管理    → 编辑公司简介、历程、文化、资质
├── 业务领域管理    → 增删改查、排序、富文本编辑
├── 工程案例管理    → 增删改查、分类、图片上传、推荐
├── 新闻动态管理    → 增删改查、分类、富文本、发布/草稿
├── 合作伙伴管理    → 增删改查、Logo 上传
├── 留言管理        → 列表、标记已读、回复
└── 站点配置        → SEO、统计代码、备案号、首页数字
```

---

## 七、API 接口设计（官网前台调用）

> 前台 API 不需要登录认证，是公开接口，加前缀 `/api/web/`

| 接口 | 方法 | 说明 |
|------|------|------|
| /api/web/banner/list | GET | 获取启用的轮播图列表 |
| /api/web/company/info | GET | 获取公司信息 |
| /api/web/business/list | GET | 获取业务领域列表 |
| /api/web/business/{id} | GET | 获取业务详情 |
| /api/web/case/list | GET | 获取案例列表（支持分类筛选、分页） |
| /api/web/case/{id} | GET | 获取案例详情 |
| /api/web/case/featured | GET | 获取首页推荐案例 |
| /api/web/news/list | GET | 获取新闻列表（支持分类筛选、分页） |
| /api/web/news/{id} | GET | 获取新闻详情 |
| /api/web/news/latest | GET | 获取最新N条新闻 |
| /api/web/partner/list | GET | 获取合作伙伴列表 |
| /api/web/message/submit | POST | 提交留言 |
| /api/web/config/stats | GET | 获取首页数字展示配置 |

---

## 八、分阶段实施计划

### 阶段一：地基搭建（数据库 + 后端骨架）

**交付物：**
- [x] MySQL 建表 SQL（schema.sql）
- [x] 水利工程模拟数据 SQL（seed.sql）
- [x] Spring Boot 项目骨架（基于 RuoYi-Vue）
- [x] CMS 业务模块代码（Entity / Mapper / Service / Controller）
- [x] docker-compose.yml（MySQL + Redis 服务）

**成功标准**：后端能启动，API 能返回模拟数据。

---

### 阶段二：管理后台

**交付物：**
- [ ] 后台 CMS 菜单和页面（基于 RuoYi 后台 + 代码生成）
- [ ] 轮播图管理页面（含图片上传）
- [ ] 新闻管理页面（含富文本编辑器）
- [ ] 工程案例管理页面
- [ ] 业务领域管理页面
- [ ] 公司信息编辑页面
- [ ] 合作伙伴管理
- [ ] 留言管理
- [ ] 站点配置

**成功标准**：管理员登录后台，能增删改查所有内容，数据存进 MySQL。

---

### 阶段三：官网前台

**交付物：**
- [ ] Nuxt 3 项目搭建
- [ ] 首页（轮播 + 简介 + 核心业务 + 数字 + 案例 + 新闻 + 合作伙伴）
- [ ] 关于我们页面
- [ ] 业务领域列表/详情页
- [ ] 工程案例列表/详情页
- [ ] 新闻中心列表/详情页
- [ ] 联系我们页面（含留言表单）
- [ ] 响应式适配（移动端）
- [ ] SEO 优化（meta、sitemap）

**成功标准**：前台能正常展示 MySQL 中的所有数据，样式完整，手机端适配。

---

### 阶段四：部署上线

**交付物：**
- [ ] Dockerfile（后端、前台）
- [ ] docker-compose.yml（完整编排：MySQL + Redis + 后端 + 后台 + 前台 + Nginx）
- [ ] Nginx 配置（前台/后台/API 路由）
- [ ] 部署文档
- [ ] 域名/SSL 配置指南

**成功标准**：`docker-compose up -d` 一键启动所有服务，外网可访问。

---

## 九、项目目录结构（最终形态）

```
company-home/
├── README.md
├── docker-compose.yml              # 整体编排
├── docs/
│   ├── PLAN.md                     # 本文档
│   └── deployment.md               # 部署指南
├── sql/
│   ├── schema.sql                  # 建表语句（RuoYi系统表 + CMS业务表）
│   └── seed.sql                    # 水利工程模拟数据
├── server/                         # 后端（Spring Boot）
│   ├── ruoyi-admin/                # 启动模块
│   ├── ruoyi-common/               # 通用模块
│   ├── ruoyi-framework/            # 框架核心
│   ├── ruoyi-system/               # 系统模块
│   ├── ruoyi-cms/                  # CMS 业务模块（新增）
│   │   ├── src/main/java/.../cms/
│   │   │   ├── controller/         # API 控制器
│   │   │   ├── domain/             # 实体类
│   │   │   ├── mapper/             # MyBatis Mapper
│   │   │   ├── service/            # 业务逻辑
│   │   │   └── vo/                 # 前端视图对象
│   │   └── src/main/resources/
│   │       └── mapper/cms/         # Mapper XML
│   └── pom.xml
├── admin/                          # 管理后台前端（Vue3 + Element Plus）
│   ├── src/
│   │   ├── views/cms/              # CMS 管理页面
│   │   │   ├── banner/
│   │   │   ├── company/
│   │   │   ├── business/
│   │   │   ├── case/
│   │   │   ├── news/
│   │   │   ├── partner/
│   │   │   ├── message/
│   │   │   └── config/
│   │   └── api/cms/                # CMS 接口定义
│   └── package.json
└── website/                        # 官网前台（Nuxt 3）
    ├── pages/
    │   ├── index.vue               # 首页
    │   ├── about.vue               # 关于我们
    │   ├── business/
    │   ├── cases/
    │   ├── news/
    │   └── contact.vue             # 联系我们
    ├── components/                  # 公共组件
    ├── composables/                 # API 调用
    ├── assets/                      # 样式/图片
    ├── nuxt.config.ts
    └── package.json
```

---

## 十、开发优先级与依赖关系

```
阶段一（地基）──→ 阶段二（后台）──→ 阶段三（前台）──→ 阶段四（部署）
     │                                    │
     └── 数据库 + API 就绪后，前后台可并行开发 ──┘
```

建议先完成阶段一，有了 API 之后，阶段二（后台）和阶段三（前台）可以并行推进。

---

## 十一、后续可扩展

完成基础官网后，可在同一套系统上扩展：

- 人才招聘模块
- 工程进度展示
- 资质证书在线查看
- 企业视频/宣传片管理
- 多语言支持（中英文）
- 数据统计仪表盘（访问量、留言趋势）
