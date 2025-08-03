# 码上启航 - 部署指南

## 环境准备

### 1. 系统要求
- **操作系统**: Linux/Windows/macOS
- **Java版本**: JDK 17+
- **内存**: 最少2GB，推荐4GB+
- **磁盘空间**: 最少1GB可用空间

### 2. 依赖服务

#### MySQL数据库
```bash
# 安装MySQL 8.0+
# Ubuntu/Debian
sudo apt update
sudo apt install mysql-server

# CentOS/RHEL
sudo yum install mysql-server

# 启动MySQL服务
sudo systemctl start mysql
sudo systemctl enable mysql
```

#### Redis缓存
```bash
# Ubuntu/Debian
sudo apt install redis-server

# CentOS/RHEL
sudo yum install redis

# 启动Redis服务
sudo systemctl start redis
sudo systemctl enable redis
```

## 数据库初始化

### 1. 创建数据库
```bash
# 登录MySQL
mysql -u root -p

# 执行SQL脚本
source database_schema.sql
```

### 2. 验证数据库
```sql
USE coding_service;
SHOW TABLES;
DESCRIBE reservation;
DESCRIBE course;
DESCRIBE student;
```

## 应用配置

### 1. 修改配置文件
编辑 `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/coding_service?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: your_mysql_username
    password: your_mysql_password
  
  data:
    redis:
      host: localhost
      port: 6379
      # password: your_redis_password  # 如果Redis设置了密码

langchain4j:
  open-ai:
    chat-model:
      api-key: "your_openai_api_key"  # 替换为您的API密钥
    streaming-chat-model:
      api-key: "your_openai_api_key"  # 替换为您的API密钥
```

### 2. 环境变量配置（可选）
```bash
export MYSQL_USERNAME=your_username
export MYSQL_PASSWORD=your_password
export OPENAI_API_KEY=your_api_key
```

## 构建和部署

### 1. 本地开发环境
```bash
# 克隆项目
git clone [项目地址]
cd computer-training-advisor

# 编译项目
mvn clean compile

# 运行测试
mvn test

# 启动应用
mvn spring-boot:run
```

### 2. 生产环境部署

#### 方式一：JAR包部署
```bash
# 打包
mvn clean package -DskipTests

# 运行
java -jar target/computer-training-advisor-0.0.1-SNAPSHOT.jar
```

#### 方式二：Docker部署
```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/computer-training-advisor-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
```

```bash
# 构建镜像
docker build -t computer-training-advisor .

# 运行容器
docker run -d -p 8080:8080 --name computer-training-advisor computer-training-advisor
```

#### 方式三：使用Docker Compose
```yaml
# docker-compose.yml
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql
      - redis
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/coding_service
      - SPRING_REDIS_HOST=redis

  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: coding_service
    volumes:
      - mysql_data:/var/lib/mysql
      - ./database_schema.sql:/docker-entrypoint-initdb.d/init.sql

  redis:
    image: redis:6-alpine

volumes:
  mysql_data:
```

```bash
# 启动服务
docker-compose up -d
```

## 验证部署

### 1. 健康检查
```bash
# 检查应用状态
curl http://localhost:8080/actuator/health

# 检查数据库连接
curl http://localhost:8080/actuator/health/db

# 检查Redis连接
curl http://localhost:8080/actuator/health/redis
```

### 2. 功能测试
1. 打开浏览器访问: http://localhost:8080
2. 测试AI对话功能
3. 测试预约功能
4. 检查流式响应

## 监控和维护

### 1. 日志管理
```bash
# 查看应用日志
tail -f logs/application.log

# 查看错误日志
grep ERROR logs/application.log
```

### 2. 性能监控
- 监控JVM内存使用情况
- 监控数据库连接池状态
- 监控Redis缓存命中率
- 监控AI API调用频率

### 3. 备份策略
```bash
# 数据库备份
mysqldump -u root -p coding_service > backup_$(date +%Y%m%d).sql

# Redis备份
redis-cli BGSAVE
```

## 故障排除

### 常见问题

1. **数据库连接失败**
   - 检查MySQL服务状态
   - 验证数据库连接配置
   - 确认防火墙设置

2. **Redis连接失败**
   - 检查Redis服务状态
   - 验证Redis配置
   - 确认网络连通性

3. **AI服务调用失败**
   - 检查API密钥配置
   - 验证网络连接
   - 查看API调用限制

4. **应用启动失败**
   - 检查Java版本
   - 验证端口占用情况
   - 查看启动日志

### 联系支持
如遇到技术问题，请联系开发团队获取支持。 