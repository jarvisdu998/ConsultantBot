package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseReview {
    private Long id;
    private Long courseId;
    private Long studentId;
    private Integer rating;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联对象
    private Course course;
    private Student student;
} 