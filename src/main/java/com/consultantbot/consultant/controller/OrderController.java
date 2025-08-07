package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Order;
import com.consultantbot.consultant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    /**
     * 获取学员的订单列表
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getStudentOrders(@PathVariable Long studentId) {
        List<Order> orders = orderService.getByStudentId(studentId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", orders);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取所有订单（管理员端）
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        List<Order> orders = orderService.getAll();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", orders);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取订单详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable Long id) {
        Order order = orderService.getById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (order != null) {
            response.put("success", true);
            response.put("data", order);
        } else {
            response.put("success", false);
            response.put("message", "订单不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 创建订单
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = orderService.createOrder(order);
        if (success) {
            response.put("success", true);
            response.put("message", "订单创建成功");
            response.put("data", order);
        } else {
            response.put("success", false);
            response.put("message", "订单创建失败");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 取消订单
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = orderService.cancelOrder(id);
        if (success) {
            response.put("success", true);
            response.put("message", "订单取消成功");
        } else {
            response.put("success", false);
            response.put("message", "订单取消失败，订单不存在");
        }
        
        return ResponseEntity.ok(response);
    }
} 