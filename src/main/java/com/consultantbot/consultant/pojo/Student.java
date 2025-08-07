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
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private String province;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status;
} 