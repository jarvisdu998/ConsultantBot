-- 初始化数据脚本

-- 插入管理员数据
INSERT INTO admin (username, password, name, phone, email) VALUES 
('admin', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', '13800138001', 'admin@example.com'),
('manager', 'e10adc3949ba59abbe56e057f20f883e', '课程经理', '13800138002', 'manager@example.com');

-- 插入学员数据
INSERT INTO student (username, password, name, gender, phone, email, province, status) VALUES 
('student001', 'e10adc3949ba59abbe56e057f20f883e', '张三', '男', '13800138003', 'zhangsan@example.com', '北京', 1),
('student002', 'e10adc3949ba59abbe56e057f20f883e', '李四', '女', '13800138004', 'lisi@example.com', '上海', 1),
('student003', 'e10adc3949ba59abbe56e057f20f883e', '王五', '男', '13800138005', 'wangwu@example.com', '广州', 1);

-- 插入课程数据
INSERT INTO course (name, description, price, duration, level, status) VALUES 
('Java基础入门', 'Java编程语言基础知识和语法', 299.00, 120, '初级', 1),
('Spring Boot实战', 'Spring Boot框架开发实战课程', 599.00, 180, '中级', 1),
('微服务架构', '微服务架构设计与实现', 899.00, 240, '高级', 1),
('数据库设计', 'MySQL数据库设计与优化', 399.00, 150, '中级', 1),
('前端开发', 'HTML、CSS、JavaScript基础', 199.00, 90, '初级', 1);

-- 插入预约数据
INSERT INTO reservation (name, phone, gender, communication_time, province, estimated_score) VALUES 
('赵六', '13800138006', '男', '2024-01-15 10:00:00', '北京', 85),
('钱七', '13800138007', '女', '2024-01-16 14:00:00', '上海', 90),
('孙八', '13800138008', '男', '2024-01-17 16:00:00', '深圳', 78);

-- 插入订单数据
INSERT INTO orders (student_id, course_id, order_no, amount) VALUES 
(1, 1, 'ORD1703123456789ABCDEF', 299.00),
(1, 2, 'ORD1703123456790ABCDEF', 599.00),
(2, 1, 'ORD1703123456791ABCDEF', 299.00),
(3, 3, 'ORD1703123456792ABCDEF', 899.00);

-- 注意：密码都是123456的MD5加密值 