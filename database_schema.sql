-- 码上启航平台数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS coding_service CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE coding_service;

-- 创建预约表
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

-- 创建课程表
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

-- 创建学员表
CREATE TABLE IF NOT EXISTS student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '学员ID',
    name VARCHAR(50) NOT NULL COMMENT '学员姓名',
    phone VARCHAR(20) UNIQUE NOT NULL COMMENT '学员电话',
    email VARCHAR(100) COMMENT '学员邮箱',
    gender VARCHAR(10) COMMENT '学员性别',
    province VARCHAR(50) COMMENT '所在地区',
    technical_level VARCHAR(20) COMMENT '技术水平',
    learning_goals TEXT COMMENT '学习目标',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学员表';

-- 创建订单表
CREATE TABLE IF NOT EXISTS `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    order_number VARCHAR(50) UNIQUE NOT NULL COMMENT '订单号',
    student_id BIGINT NOT NULL COMMENT '学员ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    amount DECIMAL(10,2) NOT NULL COMMENT '订单金额',
    status VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT '订单状态：pending-待支付，paid-已支付，completed-已完成，cancelled-已取消',
    payment_method VARCHAR(20) COMMENT '支付方式',
    payment_time DATETIME COMMENT '支付时间',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- 创建讲师表
CREATE TABLE IF NOT EXISTS instructor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '讲师ID',
    name VARCHAR(50) NOT NULL COMMENT '讲师姓名',
    title VARCHAR(100) COMMENT '职称',
    avatar VARCHAR(255) COMMENT '头像URL',
    introduction TEXT COMMENT '个人介绍',
    expertise TEXT COMMENT '专业领域',
    experience_years INT COMMENT '从业年限',
    status TINYINT DEFAULT 1 COMMENT '状态：1-在职，0-离职',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='讲师表';

-- 创建课程评价表
CREATE TABLE IF NOT EXISTS course_review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '评价ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    student_id BIGINT NOT NULL COMMENT '学员ID',
    rating INT NOT NULL COMMENT '评分：1-5星',
    content TEXT COMMENT '评价内容',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程评价表';

-- 创建企业服务表
CREATE TABLE IF NOT EXISTS enterprise_service (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '服务ID',
    name VARCHAR(100) NOT NULL COMMENT '服务名称',
    category VARCHAR(50) NOT NULL COMMENT '服务分类',
    description TEXT COMMENT '服务描述',
    price_range VARCHAR(50) COMMENT '价格范围',
    duration VARCHAR(50) COMMENT '服务时长',
    features TEXT COMMENT '服务特点',
    status TINYINT DEFAULT 1 COMMENT '状态：1-可用，0-不可用',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='企业服务表';

-- 创建企业服务预约表
CREATE TABLE IF NOT EXISTS enterprise_reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '预约ID',
    company_name VARCHAR(100) NOT NULL COMMENT '公司名称',
    contact_name VARCHAR(50) NOT NULL COMMENT '联系人姓名',
    contact_phone VARCHAR(20) NOT NULL COMMENT '联系电话',
    service_id BIGINT NOT NULL COMMENT '服务ID',
    requirement TEXT COMMENT '服务需求',
    expected_start_date DATE COMMENT '期望开始日期',
    budget_range VARCHAR(50) COMMENT '预算范围',
    status VARCHAR(20) DEFAULT 'pending' COMMENT '状态：pending-待处理，processing-处理中，completed-已完成',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (service_id) REFERENCES enterprise_service(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='企业服务预约表';

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

-- 插入示例讲师数据
INSERT INTO instructor (name, title, introduction, expertise, experience_years) VALUES
('张总经理', '创始人 & CEO', '15年互联网行业经验，前阿里巴巴技术专家，专注于Java企业级开发', 'Java全栈开发,微服务架构,企业级应用', 15),
('李总监', '教学总监', '10年教育行业经验，资深技术培训专家，擅长课程体系设计', 'Java开发,Spring框架,教学管理', 10),
('王经理', '技术总监', '8年软件开发经验，全栈技术专家，专注于前端和后端技术', '前端开发,后端开发,全栈技术', 8);

-- 插入示例企业服务数据
INSERT INTO enterprise_service (name, category, description, price_range, duration, features) VALUES
('代码审查服务', '技术咨询', '专业的代码质量检查，提升代码可维护性和性能', '5000-20000元', '1-2周', '代码规范检查,性能优化建议,安全漏洞检测'),
('数据库优化服务', '技术咨询', '数据库性能调优，提升系统整体运行效率', '8000-30000元', '2-4周', 'SQL语句优化,索引设计优化,性能监控分析'),
('安全审计服务', '安全服务', '全面的安全漏洞检测和修复建议', '10000-50000元', '1-3周', '漏洞扫描检测,渗透测试服务,安全培训指导'),
('企业技术培训', '培训服务', '为企业提供定制化的技术培训服务', '20000-100000元', '1-3个月', '定制化课程,实战项目,技术指导');

-- 创建索引
CREATE INDEX idx_reservation_phone ON reservation(phone);
CREATE INDEX idx_course_category ON course(category);
CREATE INDEX idx_course_status ON course(status);
CREATE INDEX idx_student_phone ON student(phone);
CREATE INDEX idx_order_number ON `order`(order_number);
CREATE INDEX idx_order_student ON `order`(student_id);
CREATE INDEX idx_order_status ON `order`(status);
CREATE INDEX idx_review_course ON course_review(course_id);
CREATE INDEX idx_review_student ON course_review(student_id);
CREATE INDEX idx_enterprise_service_category ON enterprise_service(category);
CREATE INDEX idx_enterprise_reservation_status ON enterprise_reservation(status); 