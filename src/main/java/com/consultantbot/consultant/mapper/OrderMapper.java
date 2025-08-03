package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    // 根据订单号查询订单
    @Select("SELECT * FROM `order` WHERE order_number = #{orderNumber}")
    Order selectByOrderNumber(String orderNumber);
    
    // 根据手机号查询订单
    @Select("SELECT o.*, s.name as student_name, s.phone as student_phone, c.name as course_name, c.price as course_price " +
            "FROM `order` o " +
            "LEFT JOIN student s ON o.student_id = s.id " +
            "LEFT JOIN course c ON o.course_id = c.id " +
            "WHERE s.phone = #{phone} ORDER BY o.create_time DESC")
    List<Order> selectByPhone(String phone);
    
    // 根据学员ID查询订单
    @Select("SELECT o.*, s.name as student_name, s.phone as student_phone, c.name as course_name, c.price as course_price " +
            "FROM `order` o " +
            "LEFT JOIN student s ON o.student_id = s.id " +
            "LEFT JOIN course c ON o.course_id = c.id " +
            "WHERE o.student_id = #{studentId} ORDER BY o.create_time DESC")
    List<Order> selectByStudentId(Long studentId);
    
    // 创建订单
    @Insert("INSERT INTO `order` (order_number, student_id, course_id, amount, status) " +
            "VALUES (#{orderNumber}, #{studentId}, #{courseId}, #{amount}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Order order);
    
    // 更新订单状态
    @Update("UPDATE `order` SET status = #{status}, payment_method = #{paymentMethod}, payment_time = #{paymentTime} " +
            "WHERE id = #{id}")
    void updateStatus(Order order);
} 