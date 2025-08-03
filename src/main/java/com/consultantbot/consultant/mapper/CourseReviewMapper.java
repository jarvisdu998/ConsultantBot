package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.CourseReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseReviewMapper {
    // 根据课程ID查询评价
    @Select("SELECT cr.*, s.name as student_name, s.phone as student_phone " +
            "FROM course_review cr " +
            "LEFT JOIN student s ON cr.student_id = s.id " +
            "WHERE cr.course_id = #{courseId} ORDER BY cr.create_time DESC")
    List<CourseReview> selectByCourseId(Long courseId);
    
    // 添加评价
    @Insert("INSERT INTO course_review (course_id, student_id, rating, content) " +
            "VALUES (#{courseId}, #{studentId}, #{rating}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(CourseReview review);
    
    // 查询课程平均评分
    @Select("SELECT AVG(rating) FROM course_review WHERE course_id = #{courseId}")
    Double selectAverageRatingByCourseId(Long courseId);
    
    // 查询课程评价数量
    @Select("SELECT COUNT(*) FROM course_review WHERE course_id = #{courseId}")
    Integer selectCountByCourseId(Long courseId);
} 