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
    private String description;
    private BigDecimal price;
    private Integer duration;
    private String level;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status;
} 