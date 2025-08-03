package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.CourseMapper;
import com.consultantbot.consultant.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    
    // 查询所有课程
    public List<Course> getAllCourses() {
        return courseMapper.selectAll();
    }
    
    // 根据分类查询课程
    public List<Course> getCoursesByCategory(String category) {
        return courseMapper.selectByCategory(category);
    }
    
    // 根据ID查询课程
    public Course getCourseById(Long id) {
        return courseMapper.selectById(id);
    }
    
    // 查询热门课程
    public List<Course> getPopularCourses() {
        return courseMapper.selectPopularCourses();
    }
} 