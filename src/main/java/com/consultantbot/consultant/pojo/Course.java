package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    private String duration;
    private String level;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 