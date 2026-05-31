# ============================================================
# RuoYi-Vue-CMS 后端 Dockerfile
# 多阶段构建：Maven 编译 + JDK 运行
# ============================================================

# --- 构建阶段 ---
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

# 先复制 pom.xml 下载依赖（利用 Docker 缓存）
COPY pom.xml .
COPY ruoyi-admin/pom.xml ruoyi-admin/pom.xml
COPY ruoyi-common/pom.xml ruoyi-common/pom.xml
COPY ruoyi-cms/pom.xml ruoyi-cms/pom.xml
COPY ruoyi-modules/pom.xml ruoyi-modules/pom.xml

# 下载依赖（这一层会被缓存）
RUN mvn dependency:go-offline -B 2>/dev/null || true

# 复制全部源码
COPY . .

# 打包（跳过测试）
RUN mvn clean package -DskipTests -B

# --- 运行阶段 ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# 复制打包产物
COPY --from=builder /build/ruoyi-admin/target/ruoyi-admin.jar app.jar

# 创建上传目录
RUN mkdir -p /app/uploadPath

# 环境变量
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV TZ=Asia/Shanghai

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
