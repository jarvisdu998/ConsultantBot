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
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据订单号查询订单
     */
    @GetMapping("/query")
    public ResponseEntity<Map<String, Object>> queryOrder(@RequestParam String orderNumber) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Order order = orderService.getOrderByOrderNumber(orderNumber);
            if (order != null) {
                response.put("success", true);
                response.put("data", order);
            } else {
                response.put("success", false);
                response.put("message", "订单不存在");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询订单失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据手机号查询订单
     */
    @GetMapping("/phone")
    public ResponseEntity<Map<String, Object>> queryOrdersByPhone(@RequestParam String phone) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Order> orders = orderService.getOrdersByPhone(phone);
            response.put("success", true);
            response.put("data", orders);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询订单失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            orderService.createOrder(order);
            response.put("success", true);
            response.put("message", "订单创建成功");
            response.put("orderNumber", order.getOrderNumber());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "创建订单失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 更新订单状态
     */
    @PostMapping("/status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(
            @RequestParam Long orderId,
            @RequestParam String status,
            @RequestParam(required = false) String paymentMethod) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            orderService.updateOrderStatus(orderId, status, paymentMethod);
            response.put("success", true);
            response.put("message", "订单状态更新成功");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "更新订单状态失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 