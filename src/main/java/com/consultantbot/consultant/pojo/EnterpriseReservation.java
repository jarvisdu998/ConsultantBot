package com.consultantbot.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseReservation {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactPhone;
    private Long serviceId;
    private String requirement;
    private LocalDate expectedStartDate;
    private String budgetRange;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联对象
    private EnterpriseService service;
} 