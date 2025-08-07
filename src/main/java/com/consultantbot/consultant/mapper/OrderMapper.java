package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    
    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order selectById(Long id);
    
    @Select("SELECT * FROM orders WHERE student_id = #{studentId}")
    List<Order> selectByStudentId(Long studentId);
    
    @Select("SELECT * FROM orders WHERE order_no = #{orderNo}")
    Order selectByOrderNo(String orderNo);
    
    @Select("SELECT * FROM orders")
    List<Order> selectAll();
    
    @Insert("INSERT INTO orders (student_id, course_id, order_no, amount) VALUES (#{studentId}, #{courseId}, #{orderNo}, #{amount})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Order order);
    
    @Delete("DELETE FROM orders WHERE id = #{id}")
    void deleteById(Long id);
    
    @Delete("DELETE FROM orders WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void deleteByStudentAndCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
} 