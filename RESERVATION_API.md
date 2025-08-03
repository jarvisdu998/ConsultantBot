# 预约API使用说明

## 概述
预约API提供了技术指导预约服务，允许学员提交预约申请并查询预约状态。

## API接口

### 1. 提交预约申请

**接口地址：** `POST /api/reservation/submit`

**请求参数：**
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

**参数说明：**
- `name` (必填): 学员姓名，最大50个字符
- `phone` (必填): 学员电话，11位手机号
- `gender` (必填): 学员性别，"男"或"女"
- `communicationTime` (必填): 预约沟通时间，格式为 "yyyy-MM-dd'T'HH:mm"
- `province` (必填): 学员所在地区
- `technicalRequirement` (可选): 技术需求描述

**响应示例：**
```json
{
    "success": true,
    "message": "预约提交成功！我们会尽快与您联系。"
}
```

### 2. 查询预约信息

**接口地址：** `GET /api/reservation/query?phone={手机号}`

**请求参数：**
- `phone` (必填): 学员手机号

**响应示例：**
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
        "technicalRequirement": "希望学习Java Web开发，包括Spring Boot框架"
    }
}
```

## 前端集成

### HTML表单示例
```html
<form id="reservationForm">
    <input type="text" id="name" name="name" placeholder="请输入您的姓名" required>
    <input type="tel" id="phone" name="phone" placeholder="请输入您的手机号" required>
    <select id="gender" name="gender" required>
        <option value="">请选择性别</option>
        <option value="男">男</option>
        <option value="女">女</option>
    </select>
    <input type="datetime-local" id="communicationTime" name="communicationTime" required>
    <select id="province" name="province" required>
        <option value="">请选择地区</option>
        <option value="北京市">北京市</option>
        <!-- 其他地区选项 -->
    </select>
    <textarea id="technicalRequirement" name="technicalRequirement" 
              placeholder="请详细描述您的技术需求或学习目标"></textarea>
    <button type="submit">提交预约</button>
</form>
```

### JavaScript提交示例
```javascript
document.getElementById('reservationForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const formData = {
        name: document.getElementById('name').value,
        phone: document.getElementById('phone').value,
        gender: document.getElementById('gender').value,
        communicationTime: document.getElementById('communicationTime').value,
        province: document.getElementById('province').value,
        technicalRequirement: document.getElementById('technicalRequirement').value
    };

    fetch('/api/reservation/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert(data.message);
            // 重置表单
            this.reset();
        } else {
            alert('提交失败：' + data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('提交失败，请稍后重试');
    });
});
```

## 数据库表结构

```sql
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
```

## 错误处理

API会返回以下错误信息：
- 必填字段缺失
- 手机号格式不正确
- 预约时间格式错误
- 数据库操作失败

## 测试

可以使用以下测试类验证API功能：
- `ReservationControllerTest`: 控制器测试
- `ReservationServiceTest`: 服务层测试

## 注意事项

1. 确保数据库连接正常
2. 预约时间不能是过去的时间
3. 手机号必须是有效的11位数字
4. 所有必填字段都必须填写
5. 建议在前端进行数据验证 