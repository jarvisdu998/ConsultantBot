package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Reservation;
import com.consultantbot.consultant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * 提交预约申请
     */
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitReservation(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 解析日期时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime communicationTime = LocalDateTime.parse(request.get("communicationTime"), formatter);
            
            // 创建预约对象
            Reservation reservation = new Reservation();
            reservation.setName(request.get("name"));
            reservation.setPhone(request.get("phone"));
            reservation.setGender(request.get("gender"));
            reservation.setCommunicationTime(communicationTime);
            reservation.setProvince(request.get("province"));
            reservation.setTechnicalRequirement(request.get("technicalRequirement"));
            
            // 保存到数据库
            reservationService.insert(reservation);
            
            response.put("success", true);
            response.put("message", "预约提交成功！我们会尽快与您联系。");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "预约提交失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据手机号查询预约
     */
    @GetMapping("/query")
    public ResponseEntity<Map<String, Object>> queryReservation(@RequestParam String phone) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Reservation reservation = reservationService.selectByPhone(phone);
            
            if (reservation != null) {
                response.put("success", true);
                response.put("data", reservation);
            } else {
                response.put("success", false);
                response.put("message", "未找到相关预约信息");
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 