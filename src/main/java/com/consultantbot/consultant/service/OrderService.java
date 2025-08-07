package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.OrderMapper;
import com.consultantbot.consultant.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    /**
     * 根据ID查询订单
     */
    public Order getById(Long id) {
        return orderMapper.selectById(id);
    }
    
    /**
     * 根据学员ID查询订单
     */
    public List<Order> getByStudentId(Long studentId) {
        return orderMapper.selectByStudentId(studentId);
    }
    
    /**
     * 查询所有订单
     */
    public List<Order> getAll() {
        return orderMapper.selectAll();
    }
    
    /**
     * 创建订单
     */
    public boolean createOrder(Order order) {
        // 生成订单号
        order.setOrderNo(generateOrderNo());
        orderMapper.insert(order);
        return true;
    }
    
    /**
     * 取消订单
     */
    public boolean cancelOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            return false;
        }
        orderMapper.deleteById(id);
        return true;
    }
    
    /**
     * 删除订单
     */
    public boolean deleteById(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            return false;
        }
        orderMapper.deleteById(id);
        return true;
    }
    
    /**
     * 生成订单号
     */
    private String generateOrderNo() {
        return "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8);
    }
} 