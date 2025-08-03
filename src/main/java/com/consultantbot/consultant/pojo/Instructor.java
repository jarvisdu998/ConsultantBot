package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    private Long id;
    private String name;
    private String title;
    private String avatar;
    private String introduction;
    private String expertise;
    private Integer experienceYears;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 