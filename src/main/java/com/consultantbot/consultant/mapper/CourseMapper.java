package com.consultantbot.consultant.mapper;

import com.consultantbot.consultant.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    
    @Select("SELECT * FROM course WHERE id = #{id}")
    Course selectById(Long id);
    
    @Select("SELECT * FROM course WHERE status = 1")
    List<Course> selectAllActive();
    
    @Select("SELECT * FROM course")
    List<Course> selectAll();
    
    @Insert("INSERT INTO course (name, description, price, duration, level) VALUES (#{name}, #{description}, #{price}, #{duration}, #{level})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Course course);
    
    @Update("UPDATE course SET name = #{name}, description = #{description}, price = #{price}, duration = #{duration}, level = #{level}, status = #{status} WHERE id = #{id}")
    void update(Course course);
    
    @Delete("DELETE FROM course WHERE id = #{id}")
    void deleteById(Long id);
    
    @Update("UPDATE course SET status = #{status} WHERE id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
} 