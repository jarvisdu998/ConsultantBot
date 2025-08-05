package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    // 根据订单号查询订单
    Order selectByOrderNumber(@Param("orderNumber") String orderNumber);
    
    // 根据手机号查询订单
    List<Order> selectByPhone(@Param("phone") String phone);
    
    // 根据用户ID查询订单
    List<Order> selectByUserId(@Param("userId") Long userId);
    
    // 查询所有订单（管理员用）
    List<Order> selectAll();
    
    // 创建订单
    void insert(Order order);
    
    // 更新订单状态
    void updateStatus(Order order);
} 