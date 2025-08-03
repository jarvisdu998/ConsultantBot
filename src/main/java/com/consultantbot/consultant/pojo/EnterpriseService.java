package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseService {
    private Long id;
    private String name;
    private String category;
    private String description;
    private String priceRange;
    private String duration;
    private String features;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 