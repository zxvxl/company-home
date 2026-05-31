# 自定义指南

本文档说明如何自定义虹远水利工程有限公司官网的外观和内容。

## 主题设置说明

主题的可配置选项定义在 `theme-engineering-company/settings.yaml` 文件中。在 Halo 后台「外观」-「主题设置」中可以通过可视化界面修改这些设置。

主要设置分组包括：

- **基本设置**：网站标题、Logo、Favicon、版权信息
- **首页设置**：轮播图、服务展示、公司简介
- **联系信息**：公司地址、电话、邮箱、地图
- **社交媒体**：微信公众号、微博等链接

## 配色方案修改

主题的配色方案定义在 `theme-engineering-company/assets/css/style.css` 文件中。

### 主要颜色变量

在 CSS 文件顶部的 `:root` 选择器中修改颜色变量：

```css
:root {
    --primary-color: #1a5276;      /* 主色调 - 深蓝色，代表水利 */
    --secondary-color: #2980b9;    /* 辅助色 - 亮蓝色 */
    --accent-color: #27ae60;       /* 强调色 - 绿色，代表生态 */
    --text-color: #333333;         /* 正文文字颜色 */
    --light-bg: #f5f7fa;           /* 浅色背景 */
    --dark-bg: #1a252f;            /* 深色背景（页脚等） */
}
```

### 建议配色方案

| 场景 | 主色调 | 辅助色 | 强调色 |
|------|--------|--------|--------|
| 默认（水利蓝） | #1a5276 | #2980b9 | #27ae60 |
| 生态绿 | #1e8449 | #27ae60 | #2980b9 |
| 工程灰 | #2c3e50 | #34495e | #e67e22 |

## Logo 与品牌替换

### 替换 Logo

1. 准备 Logo 文件：
   - 推荐格式：SVG（矢量）或 PNG（透明背景）
   - 建议尺寸：宽度 200px，高度 60px

2. 将 Logo 文件放入 `theme-engineering-company/assets/images/` 目录

3. 在 Halo 后台「外观」-「主题设置」-「基本设置」中上传新 Logo

或直接修改模板文件 `templates/modules/header.html` 中的 Logo 引用路径。

### 替换 Favicon

1. 准备 Favicon 文件（32x32 像素，ICO 或 PNG 格式）
2. 在 Halo 后台「外观」-「主题设置」-「基本设置」中上传

## 内容管理

### 文章管理

在 Halo 后台「文章」模块中管理网站内容：

1. **新建文章**：点击「新建」，输入标题和内容
2. **分类设置**：为文章指定分类（如：公司新闻、项目动态、行业资讯）
3. **标签管理**：添加标签便于内容检索
4. **封面图**：上传文章封面图，首页列表展示时使用
5. **发布控制**：可设为草稿或定时发布

### 推荐文章分类

适合水利工程公司的内容分类：

- **公司新闻** - 企业动态、获奖信息、团队活动
- **项目案例** - 已完工项目展示、在建项目进度
- **行业资讯** - 水利政策法规、行业发展动态
- **技术分享** - 施工技术、设计经验、创新方案
- **招贤纳士** - 招聘信息、企业文化

### 页面管理

独立页面适合展示固定内容：

- **关于我们** - 公司介绍、发展历程、企业资质
- **业务范围** - 服务领域详细说明
- **联系我们** - 联系方式、地图、在线留言
- **资质荣誉** - 企业证书、获奖情况

在 Halo 后台「页面」模块中管理，页面支持自定义模板。

## 菜单配置

### 修改导航菜单

1. 登录 Halo 后台
2. 进入「外观」-「菜单」
3. 可进行以下操作：
   - 添加/删除菜单项
   - 调整菜单顺序（拖拽排序）
   - 设置菜单链接（文章、页面、分类、自定义链接）
   - 配置子菜单（下拉菜单）

### 推荐菜单结构

```
首页
关于我们
├── 公司简介
├── 发展历程
├── 企业资质
├── 组织架构
业务范围
├── 水利水电工程
├── 河道治理工程
├── 农田灌溉工程
├── 水土保持工程
项目案例
├── 精品工程
├── 在建项目
新闻动态
├── 公司新闻
├── 行业资讯
人才招聘
联系我们
```

## 添加自定义页面

### 创建新页面

1. 在 Halo 后台「页面」-「新建」
2. 输入页面标题和内容
3. 在「高级设置」中可选择页面模板
4. 设置页面别名（URL 路径）
5. 发布页面

### 使用自定义模板

如需为特定页面创建独立布局：

1. 在 `theme-engineering-company/templates/` 目录下创建新模板文件，命名格式为 `page_xxx.html`
2. 模板文件结构参考：

```html
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <th:block th:replace="~{modules/header :: head}" />
</head>
<body>
    <th:block th:replace="~{modules/header :: navbar}" />

    <!-- 自定义页面内容 -->
    <main class="custom-page">
        <div class="container">
            <h1 th:text="${page.spec.title}">页面标题</h1>
            <div th:utext="${page.content.content}">页面内容</div>
        </div>
    </main>

    <th:block th:replace="~{modules/footer :: footer}" />
</body>
</html>
```

3. 在 Halo 后台创建页面时选择对应模板

## 模板修改指南

### 模板文件说明

| 文件 | 用途 |
|------|------|
| `index.html` | 网站首页 |
| `post.html` | 文章详情页 |
| `page.html` | 独立页面 |
| `archives.html` | 文章归档列表 |
| `categories.html` | 分类列表 |
| `tags.html` | 标签列表 |
| `modules/header.html` | 页头和导航栏 |
| `modules/footer.html` | 页脚 |

### Thymeleaf 常用语法

```html
<!-- 输出文本 -->
<span th:text="${variable}">默认文本</span>

<!-- 输出 HTML -->
<div th:utext="${htmlContent}">默认内容</div>

<!-- 条件判断 -->
<div th:if="${condition}">条件为真时显示</div>

<!-- 循环遍历 -->
<div th:each="item : ${list}">
    <span th:text="${item.name}">项目名称</span>
</div>

<!-- 引入片段 -->
<th:block th:replace="~{modules/header :: navbar}" />

<!-- 链接 -->
<a th:href="${link}">链接文字</a>
```

### 修改后生效

模板文件修改后：

1. 如果使用 Docker volume 挂载方式，修改即时同步到容器
2. 在 Halo 后台「外观」-「主题」中点击「刷新」清除缓存
3. 刷新浏览器页面查看效果

## 图片与媒体管理

### 上传图片

1. 在 Halo 后台「附件」模块上传图片
2. 上传后可获取图片链接，用于文章或页面中

### 图片建议规格

| 位置 | 建议尺寸 | 格式 |
|------|----------|------|
| 首页轮播图 | 1920x600px | JPG/WebP |
| 文章封面图 | 800x450px | JPG/WebP |
| 项目案例图 | 600x400px | JPG/WebP |
| Logo | 200x60px | SVG/PNG |
| Favicon | 32x32px | ICO/PNG |
