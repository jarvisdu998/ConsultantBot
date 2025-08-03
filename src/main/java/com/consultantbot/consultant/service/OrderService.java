package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.OrderMapper;
import com.consultantbot.consultant.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    
    // 根据订单号查询订单
    public Order getOrderByOrderNumber(String orderNumber) {
        return orderMapper.selectByOrderNumber(orderNumber);
    }
    
    // 根据手机号查询订单
    public List<Order> getOrdersByPhone(String phone) {
        return orderMapper.selectByPhone(phone);
    }
    
    // 根据学员ID查询订单
    public List<Order> getOrdersByStudentId(Long studentId) {
        return orderMapper.selectByStudentId(studentId);
    }
    
    // 创建订单
    public void createOrder(Order order) {
        // 生成订单号
        String orderNumber = generateOrderNumber();
        order.setOrderNumber(orderNumber);
        order.setStatus("pending");
        orderMapper.insert(order);
    }
    
    // 更新订单状态
    public void updateOrderStatus(Long orderId, String status, String paymentMethod) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(status);
        order.setPaymentMethod(paymentMethod);
        if ("paid".equals(status)) {
            order.setPaymentTime(LocalDateTime.now());
        }
        orderMapper.updateStatus(order);
    }
    
    // 生成订单号
    private String generateOrderNumber() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = String.format("%03d", new Random().nextInt(1000));
        return "ORD" + timestamp + random;
    }
} 