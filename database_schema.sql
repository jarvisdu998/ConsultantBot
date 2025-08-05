-- 码上启航平台数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS coding_service CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE coding_service;

-- 创建预约表（保持不变）
CREATE TABLE IF NOT EXISTS reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '预约ID',
    name VARCHAR(50) NOT NULL COMMENT '学员姓名',
    phone VARCHAR(20) NOT NULL COMMENT '学员电话',
    gender VARCHAR(10) NOT NULL COMMENT '学员性别',
    communication_time DATETIME NOT NULL COMMENT '预约沟通时间',
    province VARCHAR(50) NOT NULL COMMENT '学员所在地区',
    technical_requirement TEXT COMMENT '技术需求描述',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='技术指导预约表';

-- 创建课程表（保持不变）
CREATE TABLE IF NOT EXISTS course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '课程ID',
    name VARCHAR(100) NOT NULL COMMENT '课程名称',
    category VARCHAR(50) NOT NULL COMMENT '课程分类',
    description TEXT COMMENT '课程描述',
    price DECIMAL(10,2) NOT NULL COMMENT '课程价格',
    duration VARCHAR(50) COMMENT '课程时长',
    level VARCHAR(20) COMMENT '难度等级',
    status TINYINT DEFAULT 1 COMMENT '状态：1-上架，0-下架',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程表';

-- 创建用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    role ENUM('student', 'admin') NOT NULL DEFAULT 'student' COMMENT '角色：student-学员，admin-管理员',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    phone VARCHAR(20) UNIQUE NOT NULL COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    status TINYINT DEFAULT 1 COMMENT '状态：1-正常，0-禁用',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 创建订单表
CREATE TABLE IF NOT EXISTS `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    order_number VARCHAR(50) UNIQUE NOT NULL COMMENT '订单号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    amount DECIMAL(10,2) NOT NULL COMMENT '订单金额',
    status VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT '订单状态：pending-待支付，paid-已支付，completed-已完成，cancelled-已取消',
    payment_method VARCHAR(20) COMMENT '支付方式',
    payment_time DATETIME COMMENT '支付时间',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- 插入示例课程数据
INSERT INTO course (name, category, description, price, duration, level) VALUES
('Java基础入门', '后端开发', '从零开始学习Java编程语言，掌握面向对象编程思想', 299.00, '30课时', '初级'),
('Spring Boot实战', '后端开发', '学习Spring Boot框架，快速构建Web应用', 499.00, '40课时', '中级'),
('Vue.js前端开发', '前端开发', '掌握Vue.js框架，构建现代化前端应用', 399.00, '35课时', '中级'),
('Python数据分析', '数据分析', '使用Python进行数据分析和可视化', 599.00, '45课时', '中级'),
('Android开发入门', '移动开发', '学习Android应用开发，从基础到实战', 699.00, '50课时', '中级'),
('MySQL数据库设计', '数据库', '学习数据库设计和SQL优化', 299.00, '25课时', '初级'),
('算法与数据结构', '计算机基础', '掌握常用算法和数据结构', 399.00, '30课时', '中级'),
('毕业设计指导', '毕业设计', '计算机专业毕业设计全程指导', 999.00, '一对一', '高级');

-- 插入默认管理员用户
INSERT INTO user (username, password, role, name, phone, email) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'admin', '系统管理员', '13800000000', 'admin@example.com');

-- 创建索引
CREATE INDEX idx_reservation_phone ON reservation(phone);
CREATE INDEX idx_course_category ON course(category);
CREATE INDEX idx_course_status ON course(status);
CREATE INDEX idx_user_username ON user(username);
CREATE INDEX idx_user_phone ON user(phone);
CREATE INDEX idx_user_role ON user(role);
CREATE INDEX idx_order_number ON `order`(order_number);
CREATE INDEX idx_order_user ON `order`(user_id);
CREATE INDEX idx_order_status ON `order`(status); 