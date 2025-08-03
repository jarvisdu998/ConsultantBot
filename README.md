# 码上启航 - 计算机技能培训顾问系统

## 项目简介

这是一个基于Spring Boot + LangChain4j构建的智能计算机技能培训咨询系统，为码上启航品牌提供专业的AI课程顾问服务。

## 主要功能

### 1. AI课程咨询服务
- Java基础入门课程咨询
- Spring Boot实战课程咨询  
- Vue.js前端开发课程咨询
- Python数据分析课程咨询
- Android开发入门课程咨询
- MySQL数据库设计课程咨询
- 算法与数据结构课程咨询
- 毕业设计指导服务咨询

### 2. 技能培训服务
- 毕业设计指导服务
- 软件开发外包服务
- 技术答疑解惑服务
- 企业培训服务
- 技术支持服务

### 3. 智能推荐系统
- 根据用户技术水平推荐合适课程
- 个性化学习路径规划
- 课程难度匹配

### 4. 预约管理系统
- 在线预约服务
- 预约状态查询
- 学员信息管理

## 技术架构

### 后端技术栈
- **框架**: Spring Boot 3.5.0
- **AI框架**: LangChain4j 1.0.1-beta6
- **AI模型**: 阿里云通义千问 (qwen-plus)
- **数据库**: MySQL + Redis
- **ORM**: MyBatis
- **流式响应**: Spring WebFlux

### 前端技术栈
- **框架**: Vue.js 3
- **样式**: Tailwind CSS
- **图标**: Font Awesome
- **特性**: 暗黑模式、流式显示、响应式设计

## 项目结构

```
src/
├── main/
│   ├── java/com/consultantbot/consultant/
│   │   ├── aiservice/          # AI服务接口
│   │   ├── config/             # 配置类
│   │   ├── controller/         # 控制器
│   │   ├── mapper/            # MyBatis映射器
│   │   ├── pojo/              # 实体类
│   │   ├── repository/        # 数据访问层
│   │   ├── service/           # 业务逻辑层
│   │   └── tools/             # AI工具类
│   └── resources/
│       ├── content/           # 知识库文档
│       ├── prompt/            # 系统提示词
│       └── static/            # 前端静态资源
```

## 数据库设计

### 主要数据表
1. **reservation** - 技术指导预约表
2. **course** - 课程信息表
3. **student** - 学员信息表

### 课程信息
| 课程名称 | 分类 | 价格 | 时长 | 难度 |
|---------|------|------|------|------|
| Java基础入门 | 后端开发 | 299元 | 30课时 | 初级 |
| Spring Boot实战 | 后端开发 | 499元 | 40课时 | 中级 |
| Vue.js前端开发 | 前端开发 | 399元 | 35课时 | 中级 |
| Python数据分析 | 数据分析 | 599元 | 45课时 | 中级 |
| Android开发入门 | 移动开发 | 699元 | 50课时 | 中级 |
| MySQL数据库设计 | 数据库 | 299元 | 25课时 | 初级 |
| 算法与数据结构 | 计算机基础 | 399元 | 30课时 | 中级 |
| 毕业设计指导 | 毕业设计 | 999元 | 一对一 | 高级 |

## 快速开始

### 1. 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.6+

### 2. 数据库配置
```sql
-- 执行 database_schema.sql 创建数据库和表
```

### 3. 配置文件
修改 `application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/coding_service
    username: your_username
    password: your_password
```

### 4. 启动应用
```bash
mvn spring-boot:run
```

### 5. 访问系统
打开浏览器访问: http://localhost:8080

## 核心特性

### 1. RAG检索增强
- 支持PDF文档解析和向量化存储
- 基于语义相似度的智能检索
- Redis向量数据库支持

### 2. 流式对话
- 实时流式响应
- 打字机效果显示
- 支持中断响应

### 3. 会话管理
- Redis会话存储
- 多轮对话上下文保持
- 会话历史管理

### 4. 工具集成
- 预约服务工具
- 课程查询工具
- 价格查询工具

## 开发团队

- **品牌**: 码上启航
- **技术栈**: Spring Boot + LangChain4j + Vue.js
- **AI模型**: 阿里云通义千问

## 许可证

本项目仅供学习和内部使用。 