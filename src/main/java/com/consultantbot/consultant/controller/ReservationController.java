package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Reservation;
import com.consultantbot.consultant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    /**
     * 添加预约
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addReservation(@RequestBody Reservation reservation) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = reservationService.addReservation(reservation);
        if (success) {
            response.put("success", true);
            response.put("message", "预约成功");
            response.put("data", reservation);
        } else {
            response.put("success", false);
            response.put("message", "预约失败，该手机号已存在预约");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 根据手机号查询预约
     */
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Map<String, Object>> getReservationByPhone(@PathVariable String phone) {
        Reservation reservation = reservationService.getReservationByPhone(phone);
        Map<String, Object> response = new HashMap<>();
        
        if (reservation != null) {
            response.put("success", true);
            response.put("data", reservation);
        } else {
            response.put("success", false);
            response.put("message", "未找到预约信息");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取所有预约（管理员端）
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAll();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", reservations);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取预约详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (reservation != null) {
            response.put("success", true);
            response.put("data", reservation);
        } else {
            response.put("success", false);
            response.put("message", "预约不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 更新预约信息
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        Map<String, Object> response = new HashMap<>();
        
        boolean success = reservationService.update(reservation);
        if (success) {
            response.put("success", true);
            response.put("message", "更新成功");
        } else {
            response.put("success", false);
            response.put("message", "更新失败，预约不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 取消预约
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancelReservation(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = reservationService.cancelReservation(id);
        if (success) {
            response.put("success", true);
            response.put("message", "预约取消成功");
        } else {
            response.put("success", false);
            response.put("message", "预约取消失败，预约不存在");
        }
        
        return ResponseEntity.ok(response);
    }
} 