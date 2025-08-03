package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String province;
    private String technicalLevel;
    private String learningGoals;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 