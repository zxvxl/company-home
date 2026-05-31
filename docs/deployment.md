# 部署指南

本文档详细说明虹远水利工程有限公司官网的部署流程。

## 前置条件

- **操作系统**：Linux（推荐 Ubuntu 22.04 / CentOS 7+）或 macOS
- **Docker**：20.0 及以上版本
- **Docker Compose**：2.x 及以上版本
- **硬件要求**：最低 2 核 CPU、2GB 内存、20GB 磁盘空间

### 安装 Docker（如未安装）

```bash
# Ubuntu / Debian
curl -fsSL https://get.docker.com | sh
sudo systemctl enable docker
sudo systemctl start docker

# 将当前用户加入 docker 组（避免每次使用 sudo）
sudo usermod -aG docker $USER
```

### 安装 Docker Compose

Docker Compose V2 通常随 Docker Desktop 一起安装。Linux 服务器可通过以下方式安装：

```bash
sudo apt-get install docker-compose-plugin
```

## 部署步骤

### 1. 获取项目代码

```bash
git clone <仓库地址> company-home
cd company-home
```

### 2. 配置环境变量

```bash
cp .env.example .env
```

编辑 `.env` 文件，务必修改以下配置：

- `POSTGRES_PASSWORD` - 设置强数据库密码
- `HALO_SECURITY_INITIALIZER_SUPERADMINPASSWORD` - 设置强管理员密码
- `HALO_EXTERNAL_URL` - 设置为实际访问地址（如 `https://www.hongyuan-water.com`）

### 3. 启动服务

```bash
docker-compose up -d
```

查看服务状态：

```bash
docker-compose ps
```

查看日志：

```bash
# 查看所有服务日志
docker-compose logs -f

# 仅查看 Halo 日志
docker-compose logs -f halo
```

### 4. 验证部署

等待约 30 秒服务初始化完成后，访问：

- 网站前台：`http://<服务器IP>:8090`
- 管理后台：`http://<服务器IP>:8090/console`

使用 `.env` 中配置的管理员账号密码登录后台。

## 主题安装

### 方式一：Volume 挂载（推荐）

`docker-compose.yml` 中已配置将本地 `theme-engineering-company` 目录挂载到容器内主题目录。启动服务后：

1. 登录 Halo 后台
2. 进入「外观」-「主题」
3. 找到「虹远水利工程」主题
4. 点击「激活」

### 方式二：ZIP 上传

```bash
# 打包主题
cd theme-engineering-company
zip -r ../theme-engineering-company.zip .
cd ..
```

1. 登录 Halo 后台
2. 进入「外观」-「主题」-「安装」
3. 选择「本地上传」
4. 上传 `theme-engineering-company.zip`
5. 激活主题

## 反向代理配置（Nginx）

生产环境建议使用 Nginx 作为反向代理：

### 安装 Nginx

```bash
sudo apt-get install nginx
```

### 配置文件示例

创建 `/etc/nginx/conf.d/halo.conf`：

```nginx
server {
    listen 80;
    server_name www.hongyuan-water.com;

    # 重定向到 HTTPS
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name www.hongyuan-water.com;

    # SSL 证书配置
    ssl_certificate /etc/nginx/ssl/fullchain.pem;
    ssl_certificate_key /etc/nginx/ssl/privkey.pem;
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;

    # 文件上传大小限制
    client_max_body_size 50m;

    location / {
        proxy_pass http://127.0.0.1:8090;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

### 重启 Nginx

```bash
sudo nginx -t
sudo systemctl reload nginx
```

## SSL 证书配置

### 使用 Let's Encrypt 免费证书

```bash
# 安装 certbot
sudo apt-get install certbot python3-certbot-nginx

# 获取并配置证书
sudo certbot --nginx -d www.hongyuan-water.com

# 证书自动续期（certbot 会自动添加定时任务）
sudo certbot renew --dry-run
```

### 使用自签名证书（测试环境）

```bash
sudo mkdir -p /etc/nginx/ssl
sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
    -keyout /etc/nginx/ssl/privkey.pem \
    -out /etc/nginx/ssl/fullchain.pem \
    -subj "/CN=www.hongyuan-water.com"
```

## 备份与恢复

### 数据备份

建议定期备份以下内容：

```bash
#!/bin/bash
# backup.sh - 定时备份脚本
BACKUP_DIR="/backup/halo/$(date +%Y%m%d)"
mkdir -p $BACKUP_DIR

# 备份数据库
docker exec halo_database pg_dump -U halo halo > $BACKUP_DIR/database.sql

# 备份 Halo 数据目录
docker cp halo:/root/.halo2 $BACKUP_DIR/halo_data

# 保留最近 30 天备份
find /backup/halo -type d -mtime +30 -exec rm -rf {} +

echo "备份完成: $BACKUP_DIR"
```

添加定时任务（每天凌晨 2 点执行）：

```bash
crontab -e
# 添加以下行
0 2 * * * /path/to/backup.sh >> /var/log/halo-backup.log 2>&1
```

### 数据恢复

```bash
# 停止服务
docker-compose down

# 恢复数据库
docker-compose up -d database
sleep 10
cat /backup/halo/20240101/database.sql | docker exec -i halo_database psql -U halo halo

# 恢复 Halo 数据
docker cp /backup/halo/20240101/halo_data/. halo:/root/.halo2/

# 重启所有服务
docker-compose up -d
```

## 常见问题排查

### 服务无法启动

```bash
# 查看详细日志
docker-compose logs halo
docker-compose logs database

# 检查端口占用
sudo lsof -i :8090
sudo lsof -i :5432
```

### 数据库连接失败

- 确认 `.env` 中数据库配置与 `docker-compose.yml` 一致
- 确认 PostgreSQL 容器健康状态：`docker-compose ps`
- 等待数据库完全启动后再启动 Halo（已通过 `depends_on` + `healthcheck` 处理）

### 主题未显示

- 确认 `theme-engineering-company` 目录结构完整
- 检查 `theme.yaml` 文件格式是否正确
- 在 Halo 后台「外观」-「主题」中刷新主题列表

### 上传文件失败

- 检查 Nginx `client_max_body_size` 配置
- 确认 Halo 数据目录权限正确

### 容器内存不足

```bash
# 查看容器资源使用
docker stats

# 如需限制内存，在 docker-compose.yml 中添加
# deploy:
#   resources:
#     limits:
#       memory: 1G
```

## 更新升级

### 升级 Halo 版本

```bash
# 备份数据
./backup.sh

# 拉取新版本镜像
docker-compose pull halo

# 重启服务
docker-compose up -d
```

### 更新主题

修改本地 `theme-engineering-company` 目录中的文件后：

1. 重启 Halo 容器：`docker-compose restart halo`
2. 或在后台「外观」-「主题」中刷新主题缓存
