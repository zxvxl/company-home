# 水利工程公司官网 - 基于 Halo CMS

虹远水利工程有限公司官方网站项目，基于 Halo 2.x 内容管理系统构建，包含自定义主题和示例数据。

## 技术栈

- **CMS**: [Halo 2.x](https://www.halo.run/) - 强大的开源建站工具
- **数据库**: PostgreSQL 15
- **模板引擎**: Thymeleaf
- **部署方式**: Docker + Docker Compose
- **前端**: 原生 HTML5 / CSS3 / JavaScript（响应式设计）

## 目录结构

```
company-home/
├── README.md                          # 项目说明文档
├── docker-compose.yml                 # Docker Compose 编排配置
├── .env.example                       # 环境变量示例文件
├── theme-engineering-company/         # 自定义 Halo 主题
│   ├── theme.yaml                     # 主题元信息配置
│   ├── settings.yaml                  # 主题设置选项定义
│   ├── templates/                     # Thymeleaf 模板文件
│   │   ├── index.html                 # 首页模板
│   │   ├── post.html                  # 文章详情模板
│   │   ├── page.html                  # 自定义页面模板
│   │   ├── archives.html              # 归档页模板
│   │   ├── categories.html            # 分类页模板
│   │   ├── tags.html                  # 标签页模板
│   │   └── modules/                   # 公共模块
│   │       ├── header.html            # 页头导航
│   │       └── footer.html            # 页脚信息
│   └── assets/                        # 静态资源
│       ├── css/style.css              # 全局样式
│       └── js/main.js                 # 交互脚本
├── init-data/                         # 示例数据（用于演示）
│   ├── posts/                         # 文章数据
│   └── pages/                         # 页面数据
└── docs/                              # 项目文档
    ├── deployment.md                  # 部署指南
    └── customization.md               # 自定义指南
```

## 快速开始

### 前置条件

- Docker 20.0 及以上版本
- Docker Compose 2.x 及以上版本

### 启动步骤

1. 克隆项目到本地：

```bash
git clone <仓库地址> company-home
cd company-home
```

2. 复制环境变量文件并修改配置：

```bash
cp .env.example .env
# 编辑 .env 文件，修改数据库密码和管理员信息
```

3. 启动服务：

```bash
docker-compose up -d
```

4. 访问网站：

- 前台地址：http://localhost:8090
- 后台管理：http://localhost:8090/console

默认管理员账号密码请查看 `.env` 文件中的配置。

## 主题安装

服务启动后，需要将自定义主题安装到 Halo 中：

1. 将 `theme-engineering-company` 目录打包为 ZIP 文件：

```bash
cd theme-engineering-company
zip -r ../theme-engineering-company.zip .
```

2. 登录 Halo 后台管理面板
3. 进入「外观」-「主题」-「安装」
4. 上传 `theme-engineering-company.zip` 文件
5. 激活主题

或者通过 Docker 挂载方式（已在 docker-compose.yml 中配置）：

主题目录已通过 volume 挂载到 Halo 容器内的主题目录，启动后可直接在后台激活。

## 开发说明

- 主题模板采用 Thymeleaf 语法，修改后需在 Halo 后台刷新主题缓存
- CSS 和 JS 文件修改后即时生效（浏览器可能需要清除缓存）
- 如需修改主题设置选项，请编辑 `settings.yaml` 文件
- 数据库数据默认持久化在 Docker volume 中

## 相关文档

- [部署指南](docs/deployment.md)
- [自定义指南](docs/customization.md)
- [Halo 官方文档](https://docs.halo.run/)

## 关于公司

虹远水利工程有限公司专注于水利工程领域，业务范围涵盖：

- 水利水电工程设计与施工
- 河道治理与防洪工程
- 农田灌溉工程
- 水土保持工程
- 市政给排水工程
- 水利工程咨询与监理

## 许可证

本项目仅供内部使用，未经授权不得对外分发。
