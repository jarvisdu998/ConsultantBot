# 码上启航平台API文档

## 概述
本文档描述了码上启航平台的完整后端API接口，包括课程管理、订单管理、讲师管理、评价系统、企业服务等功能。

## 基础信息
- **基础URL**: `http://localhost:8080`
- **数据格式**: JSON
- **字符编码**: UTF-8
- **跨域支持**: 所有接口都支持跨域访问

## 1. 预约管理 API

### 1.1 提交预约申请
- **接口**: `POST /api/reservation/submit`
- **描述**: 提交技术指导预约申请
- **请求参数**:
```json
{
    "name": "学员姓名",
    "phone": "学员电话",
    "gender": "学员性别",
    "communicationTime": "预约沟通时间",
    "province": "学员所在地区",
    "technicalRequirement": "技术需求描述"
}
```
- **响应示例**:
```json
{
    "success": true,
    "message": "预约提交成功！我们会尽快与您联系。"
}
```

### 1.2 查询预约信息
- **接口**: `GET /api/reservation/query?phone={手机号}`
- **描述**: 根据手机号查询预约信息
- **响应示例**:
```json
{
    "success": true,
    "data": {
        "id": 1,
        "name": "张同学",
        "phone": "13800138000",
        "gender": "男",
        "communicationTime": "2024-01-15T09:00:00",
        "province": "江苏省",
        "technicalRequirement": "希望学习Java Web开发"
    }
}
```

## 2. 课程管理 API

### 2.1 获取所有课程
- **接口**: `GET /api/course/list`
- **描述**: 获取所有上架的课程
- **响应示例**:
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "name": "Java基础入门",
            "category": "后端开发",
            "description": "从零开始学习Java编程语言",
            "price": 299.00,
            "duration": "30课时",
            "level": "初级",
            "status": 1
        }
    ]
}
```

### 2.2 根据分类获取课程
- **接口**: `GET /api/course/category/{category}`
- **描述**: 根据课程分类获取课程列表
- **路径参数**: `category` - 课程分类
- **响应格式**: 同上

### 2.3 获取热门课程
- **接口**: `GET /api/course/popular`
- **描述**: 获取热门课程（按价格排序，取前6个）
- **响应格式**: 同上

### 2.4 获取课程详情
- **接口**: `GET /api/course/{id}`
- **描述**: 根据课程ID获取课程详情
- **路径参数**: `id` - 课程ID
- **响应格式**: 同上

## 3. 订单管理 API

### 3.1 根据订单号查询订单
- **接口**: `GET /api/order/query?orderNumber={订单号}`
- **描述**: 根据订单号查询订单详情
- **响应示例**:
```json
{
    "success": true,
    "data": {
        "id": 1,
        "orderNumber": "ORD20241201001",
        "studentId": 1,
        "courseId": 1,
        "amount": 299.00,
        "status": "paid",
        "paymentMethod": "微信支付",
        "paymentTime": "2024-12-01T10:30:00",
        "createTime": "2024-12-01T10:00:00"
    }
}
```

### 3.2 根据手机号查询订单
- **接口**: `GET /api/order/phone?phone={手机号}`
- **描述**: 根据手机号查询该学员的所有订单
- **响应示例**:
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "orderNumber": "ORD20241201001",
            "studentId": 1,
            "courseId": 1,
            "amount": 299.00,
            "status": "paid",
            "studentName": "张同学",
            "studentPhone": "13800138000",
            "courseName": "Java基础入门",
            "coursePrice": 299.00
        }
    ]
}
```

### 3.3 创建订单
- **接口**: `POST /api/order/create`
- **描述**: 创建新订单
- **请求参数**:
```json
{
    "studentId": 1,
    "courseId": 1,
    "amount": 299.00
}
```
- **响应示例**:
```json
{
    "success": true,
    "message": "订单创建成功",
    "orderNumber": "ORD202412011234567890"
}
```

### 3.4 更新订单状态
- **接口**: `POST /api/order/status`
- **描述**: 更新订单状态
- **请求参数**:
  - `orderId`: 订单ID
  - `status`: 订单状态 (pending/paid/completed/cancelled)
  - `paymentMethod`: 支付方式 (可选)

## 4. 讲师管理 API

### 4.1 获取所有讲师
- **接口**: `GET /api/instructor/list`
- **描述**: 获取所有在职讲师
- **响应示例**:
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "name": "张总经理",
            "title": "创始人 & CEO",
            "introduction": "15年互联网行业经验，前阿里巴巴技术专家",
            "expertise": "Java全栈开发,微服务架构,企业级应用",
            "experienceYears": 15,
            "status": 1
        }
    ]
}
```

### 4.2 获取讲师详情
- **接口**: `GET /api/instructor/{id}`
- **描述**: 根据讲师ID获取讲师详情
- **路径参数**: `id` - 讲师ID

## 5. 课程评价 API

### 5.1 获取课程评价
- **接口**: `GET /api/review/course/{courseId}`
- **描述**: 根据课程ID获取评价列表和统计信息
- **响应示例**:
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "courseId": 1,
            "studentId": 1,
            "rating": 5,
            "content": "课程内容很实用，老师讲解很清晰",
            "studentName": "张同学",
            "studentPhone": "13800138000",
            "createTime": "2024-12-01T10:00:00"
        }
    ],
    "averageRating": 4.8,
    "reviewCount": 10
}
```

### 5.2 添加课程评价
- **接口**: `POST /api/review/add`
- **描述**: 添加课程评价
- **请求参数**:
```json
{
    "courseId": 1,
    "studentId": 1,
    "rating": 5,
    "content": "课程内容很实用，老师讲解很清晰"
}
```

## 6. 企业服务 API

### 6.1 获取所有企业服务
- **接口**: `GET /api/enterprise/services`
- **描述**: 获取所有可用的企业服务
- **响应示例**:
```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "name": "代码审查服务",
            "category": "技术咨询",
            "description": "专业的代码质量检查，提升代码可维护性和性能",
            "priceRange": "5000-20000元",
            "duration": "1-2周",
            "features": "代码规范检查,性能优化建议,安全漏洞检测",
            "status": 1
        }
    ]
}
```

### 6.2 根据分类获取企业服务
- **接口**: `GET /api/enterprise/services/category/{category}`
- **描述**: 根据服务分类获取企业服务列表
- **路径参数**: `category` - 服务分类

### 6.3 获取企业服务详情
- **接口**: `GET /api/enterprise/services/{id}`
- **描述**: 根据服务ID获取企业服务详情
- **路径参数**: `id` - 服务ID

## 错误处理

所有API都会返回统一的错误格式：
```json
{
    "success": false,
    "message": "错误描述信息"
}
```

常见错误码：
- 400: 请求参数错误
- 404: 资源不存在
- 500: 服务器内部错误

## 数据库表结构

### 主要表结构
1. **reservation** - 预约表
2. **course** - 课程表
3. **student** - 学员表
4. **order** - 订单表
5. **instructor** - 讲师表
6. **course_review** - 课程评价表
7. **enterprise_service** - 企业服务表
8. **enterprise_reservation** - 企业服务预约表

详细的数据库结构请参考 `database_schema.sql` 文件。

## 使用示例

### 前端调用示例

```javascript
// 获取热门课程
fetch('/api/course/popular')
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            console.log('热门课程:', data.data);
        } else {
            console.error('获取失败:', data.message);
        }
    });

// 提交预约
fetch('/api/reservation/submit', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        name: '张同学',
        phone: '13800138000',
        gender: '男',
        communicationTime: '2024-12-02T09:00',
        province: '江苏省',
        technicalRequirement: '希望学习Java开发'
    })
})
.then(response => response.json())
.then(data => {
    if (data.success) {
        alert('预约提交成功！');
    } else {
        alert('预约失败：' + data.message);
    }
});
```

## 注意事项

1. 所有接口都支持跨域访问
2. 时间格式统一使用 ISO 8601 格式
3. 金额字段使用 BigDecimal 类型，精确到分
4. 建议在前端进行数据验证
5. 所有接口都会返回统一的响应格式 