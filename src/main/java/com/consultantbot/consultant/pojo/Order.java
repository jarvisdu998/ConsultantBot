package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String orderNo;
    private BigDecimal amount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联查询字段
    private Student student;
    private Course course;
} 