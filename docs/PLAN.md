# 技术方案文档

## 项目名称

虹远水利工程有限公司 — 企业官网系统

## 技术选型

### 为什么选 RuoYi-Vue-CMS？

| 考量 | 决策 |
|------|------|
| 需要完整的 CMS 功能 | RuoYi-Vue-CMS 提供栏目管理、内容管理、发布审核等完整链路 |
| 后台不需要从零开发 | 框架已包含用户、权限、字典等通用功能 |
| Java 生态成熟 | Spring Boot 3 + MyBatis-Plus，企业级稳定性 |
| 可交付给客户维护 | 后台 UI 开箱即用，运营人员可直接使用 |

### 为什么前台用 Nuxt3？

| 考量 | 决策 |
|------|------|
| SEO 需求 | SSR 渲染，对搜索引擎友好 |
| 用户体验 | SPA 式的页面切换，加载速度快 |
| TypeScript | 类型安全，代码可维护性好 |
| 与 CMS 解耦 | 前台独立部署，通过 API 获取数据 |

## 架构设计

### 前后端分离 + API 对接

```
Nuxt3 前台 ─── HTTP ───► CMS /api/web/* ───► MySQL
                             │
                          不需认证
                       (无@Priv注解)
```

### 数据流

1. CMS 后台运营人员通过管理界面录入内容（新闻、案例、业务介绍等）
2. 内容审核通过后标记为"已发布"状态（status=30）
3. 前台 Nuxt3 通过 `/api/web/*` 接口获取已发布内容
4. Nuxt3 使用 SSR 渲染页面返回给用户

### 栏目 → 页面映射建议

| CMS 栏目 | 前台页面 | 说明 |
|----------|----------|------|
| 关于我们 | /about | 公司介绍长文 |
| 业务领域 | /business | 6个子栏目对应6大业务 |
| 工程案例 | /cases | 每个案例一篇内容 |
| 新闻中心 | /news | 分"公司动态""行业新闻"等子栏目 |
| 联系我们 | /contact | 静态页面 + 留言功能 |

## 部署方案

### 开发环境

- 本地 MySQL + Redis
- CMS 后端: `mvn spring-boot:run`
- 前台: `npm run dev`

### 生产环境

- Docker Compose 一键启动
- Nginx 做统一入口（端口 80）
- 前台 SSR 模式运行（Node.js 进程）
- CMS 后端 Spring Boot jar 部署

### 域名规划建议

```
www.hongyuan-water.com       → Nginx:80 → Website:3000
www.hongyuan-water.com/admin → Nginx:80 → CMS:8080
api.hongyuan-water.com       → Nginx:80 → CMS:8080/api/
```

## 待完善项

- [ ] CMS 初始化 SQL（创建站点、栏目、示例内容）
- [ ] WebsiteApiController 根据编译情况修正
- [ ] 前台详情页（点击案例/新闻进入详情）
- [ ] 图片上传后前台展示路径配置
- [ ] SSL 证书配置（生产环境）
- [ ] 前台 SEO meta 动态化
- [ ] 留言功能对接 CMS 接口
