package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    // 查询所有课程
    @Select("SELECT * FROM course WHERE status = 1 ORDER BY create_time DESC")
    List<Course> selectAll();
    
    // 根据分类查询课程
    @Select("SELECT * FROM course WHERE category = #{category} AND status = 1 ORDER BY create_time DESC")
    List<Course> selectByCategory(String category);
    
    // 根据ID查询课程
    @Select("SELECT * FROM course WHERE id = #{id}")
    Course selectById(Long id);
    
    // 查询热门课程（按价格排序，取前6个）
    @Select("SELECT * FROM course WHERE status = 1 ORDER BY price DESC LIMIT 6")
    List<Course> selectPopularCourses();
} 