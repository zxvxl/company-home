# 虹远水利工程有限公司 - 企业官网系统

## 项目简介

基于 **RuoYi-Vue-CMS** + **Nuxt3** 构建的企业官网系统，包含内容管理后台和官网前台展示。

## 系统架构

```
┌─────────────────────────────────────────────────────────┐
│                      Nginx (端口 80)                      │
├────────────────────────┬────────────────────────────────┤
│    官网前台 (/)         │    CMS API (/api/)             │
│    Nuxt3 SSR           │    后台管理 (/admin/)           │
│    Port 3000           │    Port 8080                    │
├────────────────────────┼────────────────────────────────┤
│        website/        │         cms/                    │
│      (Nuxt3 前台)       │    (RuoYi-Vue-CMS)             │
├────────────────────────┴────────────────────────────────┤
│              MySQL 8.0  +  Redis 7                       │
└─────────────────────────────────────────────────────────┘
```

## 目录结构

```
company-home/
├── cms/                          # RuoYi-Vue-CMS 完整源码
│   ├── ruoyi-admin/              # 后台管理主模块
│   ├── ruoyi-cms/                # CMS 内容管理模块
│   │   └── ruoyi-cms-contentcore/
│   │       └── controller/front/ # 官网前台公开API
│   ├── ruoyi-common/             # 公共模块
│   ├── ruoyi-modules/            # 系统模块
│   └── pom.xml                   # Maven 根配置
├── website/                      # Nuxt3 官网前台
│   ├── pages/                    # 页面
│   ├── components/               # 组件
│   ├── composables/              # 组合式函数（API调用）
│   ├── assets/css/               # 样式
│   ├── nuxt.config.ts            # Nuxt 配置
│   ├── Dockerfile                # 前台容器化
│   └── package.json
├── nginx/
│   └── default.conf              # Nginx 反向代理配置
├── docker-compose.yml            # Docker 编排
├── .env.example                  # 环境变量模板
├── docs/
│   └── PLAN.md                   # 技术方案文档
└── README.md
```

## 技术栈

| 层级 | 技术 | 说明 |
|------|------|------|
| 官网前台 | Nuxt3 + Vue3 + TypeScript | SSR/SSG 渲染，SEO 友好 |
| CMS 后端 | Spring Boot 3 + MyBatis-Plus | RuoYi-Vue-CMS 框架 |
| CMS 前端 | Vue3 + Element Plus | 后台管理界面（CMS自带） |
| 数据库 | MySQL 8.0 | 数据持久化 |
| 缓存 | Redis 7 | 会话管理、数据缓存 |
| Web 服务器 | Nginx | 反向代理、静态资源 |
| 容器化 | Docker + Docker Compose | 一键部署 |

## 快速启动

### 前置条件

- JDK 17+
- Maven 3.8+
- Node.js 18+
- Docker & Docker Compose（生产部署用）
- MySQL 8.0
- Redis 7

### 步骤一：启动基础服务

```bash
# 复制环境变量
cp .env.example .env

# 启动 MySQL 和 Redis
docker compose up -d mysql redis
```

### 步骤二：启动 CMS 后端

```bash
cd cms

# 修改数据库连接配置
# vim ruoyi-admin/src/main/resources/application-dev.yml

# 打包
mvn clean package -DskipTests

# 运行
java -jar ruoyi-admin/target/ruoyi-admin.jar
```

CMS 后台访问地址：http://localhost:8080
默认账号：admin / admin123

### 步骤三：启动官网前台

```bash
cd website

# 安装依赖
npm install

# 开发模式
npm run dev
```

官网前台访问地址：http://localhost:3000

### 步骤四：生产部署（Docker）

```bash
# 先打包 CMS
cd cms && mvn clean package -DskipTests && cd ..

# 取消 docker-compose.yml 中 cms-server 的注释

# 一键启动所有服务
docker compose up -d
```

## 官网前台页面

| 页面 | 路由 | 说明 |
|------|------|------|
| 首页 | `/` | Hero Banner、公司简介、业务领域、数字成就、案例、新闻 |
| 关于我们 | `/about` | 公司概况、发展历程、资质荣誉、企业文化 |
| 业务领域 | `/business` | 六大核心业务详细介绍 |
| 工程案例 | `/cases` | 精品工程展示，支持分类筛选 |
| 新闻中心 | `/news` | 公司动态、行业新闻、工程动态 |
| 联系我们 | `/contact` | 联系方式、在线留言、分支机构 |

## CMS 后台管理功能

- **站点管理**：多站点支持
- **栏目管理**：树形栏目结构，对应前台页面
- **内容管理**：文章发布、编辑、审核、定时发布
- **资源管理**：图片、文件上传管理
- **模板管理**：页面模板配置
- **系统管理**：用户、角色、权限、字典等

## 官网 API 接口

CMS 提供以下公开 API 供前台调用（无需认证）：

| 接口 | 方法 | 说明 |
|------|------|------|
| `/api/web/site/info` | GET | 获取站点信息 |
| `/api/web/catalogs` | GET | 获取栏目列表 |
| `/api/web/catalog/{id}/contents` | GET | 获取栏目下的内容列表 |
| `/api/web/content/{id}` | GET | 获取内容详情 |
| `/api/web/contents/featured` | GET | 获取推荐内容 |

## 部署架构

```
                  用户浏览器
                      │
                      ▼
              ┌──────────────┐
              │    Nginx:80   │
              └──────┬───────┘
                     │
         ┌───────────┼────────────┐
         │           │            │
         ▼           ▼            ▼
   ┌──────────┐ ┌─────────┐ ┌──────────┐
   │ Website  │ │ CMS API │ │  Admin   │
   │  :3000   │ │  :8080  │ │  :8080   │
   └──────────┘ └────┬────┘ └──────────┘
                     │
              ┌──────┴───────┐
              │              │
              ▼              ▼
        ┌──────────┐  ┌──────────┐
        │  MySQL   │  │  Redis   │
        │  :3306   │  │  :6379   │
        └──────────┘  └──────────┘
```

## 开发说明

### 前台开发

前台使用 Nuxt3，通过 `composables/useApi.ts` 调用 CMS 公开 API。
页面内包含 fallback 静态数据，即使 API 不可用也能展示基本内容。

### CMS 二次开发

公开 API 位于：
```
cms/ruoyi-cms/ruoyi-cms-contentcore/src/main/java/com/ruoyi/contentcore/controller/front/WebsiteApiController.java
```

如需添加新的前台接口，在该目录下新增 Controller，不加 `@Priv` 注解即可免认证访问。

## License

本项目基于 [RuoYi-Vue-CMS](https://gitee.com/meitanzai/ruoyi-vue-cms)（MIT License）构建。
