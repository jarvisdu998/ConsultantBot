# 课程管理系统API接口文档

## 基础信息
- 基础URL: `http://localhost:8080`
- 请求格式: JSON
- 响应格式: JSON

## 通用响应格式
```json
{
  "success": true/false,
  "message": "响应消息",
  "data": "响应数据"
}
```

## 1. 学员相关接口

### 1.1 学员登录
- **URL**: `POST /api/student/login`
- **请求体**:
```json
{
  "username": "学员用户名",
  "password": "密码"
}
```
- **响应**:
```json
{
  "success": true,
  "message": "登录成功",
  "data": {
    "id": 1,
    "username": "student001",
    "name": "张三",
    "phone": "13800138000",
    "email": "zhangsan@example.com",
    "province": "北京",
    "status": 1
  }
}
```

### 1.2 学员注册
- **URL**: `POST /api/student/register`
- **请求体**:
```json
{
  "username": "学员用户名",
  "password": "密码",
  "name": "真实姓名",
  "gender": "男",
  "phone": "手机号",
  "email": "邮箱",
  "province": "所在省份"
}
```

### 1.3 获取学员信息
- **URL**: `GET /api/student/{id}`

### 1.4 更新学员信息
- **URL**: `PUT /api/student/{id}`

## 2. 管理员相关接口

### 2.1 管理员登录
- **URL**: `POST /api/admin/login`
- **请求体**:
```json
{
  "username": "管理员用户名",
  "password": "密码"
}
```

### 2.2 获取所有管理员
- **URL**: `GET /api/admin`

### 2.3 添加管理员
- **URL**: `POST /api/admin`

### 2.4 更新管理员信息
- **URL**: `PUT /api/admin/{id}`

### 2.5 删除管理员
- **URL**: `DELETE /api/admin/{id}`

## 3. 课程相关接口

### 3.1 获取上架课程（学员端）
- **URL**: `GET /api/course/active`

### 3.2 获取所有课程（管理员端）
- **URL**: `GET /api/course`

### 3.3 获取课程详情
- **URL**: `GET /api/course/{id}`

### 3.4 添加课程
- **URL**: `POST /api/course`
- **请求体**:
```json
{
  "name": "课程名称",
  "description": "课程描述",
  "price": 299.00,
  "duration": 120,
  "level": "初级"
}
```

### 3.5 更新课程信息
- **URL**: `PUT /api/course/{id}`

### 3.6 删除课程
- **URL**: `DELETE /api/course/{id}`

### 3.7 更新课程状态
- **URL**: `PUT /api/course/{id}/status`
- **请求体**:
```json
{
  "status": 1  // 1=上架，0=下架
}
```

## 4. 订单相关接口

### 4.1 获取学员订单列表
- **URL**: `GET /api/order/student/{studentId}`

### 4.2 获取所有订单（管理员端）
- **URL**: `GET /api/order`

### 4.3 获取订单详情
- **URL**: `GET /api/order/{id}`

### 4.4 创建订单
- **URL**: `POST /api/order`
- **请求体**:
```json
{
  "studentId": 1,
  "courseId": 1,
  "amount": 299.00
}
```

### 4.5 取消订单
- **URL**: `DELETE /api/order/{id}`

## 5. 预约相关接口

### 5.1 添加预约
- **URL**: `POST /api/reservation`
- **请求体**:
```json
{
  "name": "学员姓名",
  "phone": "手机号",
  "gender": "男",
  "communicationTime": "2024-01-15T10:00:00",
  "province": "北京",
  "estimatedScore": 85
}
```

### 5.2 根据手机号查询预约
- **URL**: `GET /api/reservation/phone/{phone}`

### 5.3 获取所有预约（管理员端）
- **URL**: `GET /api/reservation`

### 5.4 获取预约详情
- **URL**: `GET /api/reservation/{id}`

### 5.5 更新预约信息
- **URL**: `PUT /api/reservation/{id}`

### 5.6 取消预约
- **URL**: `DELETE /api/reservation/{id}`

## 6. 管理员管理学员接口

### 6.1 获取所有学员
- **URL**: `GET /api/admin/student`

### 6.2 获取学员详情
- **URL**: `GET /api/admin/student/{id}`

### 6.3 添加学员
- **URL**: `POST /api/admin/student`

### 6.4 更新学员信息
- **URL**: `PUT /api/admin/student/{id}`

### 6.5 删除学员
- **URL**: `DELETE /api/admin/student/{id}`

### 6.6 更新学员状态
- **URL**: `PUT /api/admin/student/{id}/status`
- **请求体**:
```json
{
  "status": 1  // 1=正常，0=禁用
}
```

## 功能说明

### 学员功能
- ✅ 登录
- ✅ 注册
- ✅ 预约
- ✅ 查看预约
- ✅ 取消预约
- ✅ 购买课程
- ✅ 查看订单
- ✅ 取消订单

### 管理员功能
- ✅ 登录
- ✅ CRUD学员
- ✅ CRUD课程
- ✅ CRUD订单
- ✅ CRUD预约
- ✅ 管理其他管理员

## 数据库表结构

### student（学员表）
- id: 主键ID
- username: 登录用户名
- password: 密码（加密存储）
- name: 真实姓名
- gender: 性别
- phone: 手机号
- email: 邮箱
- province: 所在省份
- create_time: 注册时间
- update_time: 更新时间
- status: 状态（1=正常，0=禁用）

### admin（管理员表）
- id: 主键ID
- username: 管理员用户名
- password: 密码（加密存储）
- name: 真实姓名
- phone: 手机号
- email: 邮箱

### course（课程表）
- id: 主键ID
- name: 课程名称
- description: 课程描述
- price: 课程价格
- duration: 课程时长（分钟）
- level: 课程难度（初级/中级/高级）
- create_time: 创建时间
- update_time: 更新时间
- status: 状态（1=上架，0=下架）

### orders（订单表）
- id: 主键ID
- student_id: 学员ID
- course_id: 课程ID
- order_no: 订单编号
- amount: 支付金额
- create_time: 下单时间
- update_time: 更新时间

### reservation（预约表）
- id: 主键ID
- name: 学员姓名
- gender: 学员性别
- phone: 学员手机号
- communication_time: 沟通时间
- province: 学员所处的省份
- estimated_score: 学员需求 