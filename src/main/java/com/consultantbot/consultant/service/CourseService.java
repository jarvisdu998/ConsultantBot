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
    
    /**
     * 根据ID查询课程
     */
    public Course getById(Long id) {
        return courseMapper.selectById(id);
    }
    
    /**
     * 查询所有上架课程
     */
    public List<Course> getAllActive() {
        return courseMapper.selectAllActive();
    }
    
    /**
     * 查询所有课程
     */
    public List<Course> getAll() {
        return courseMapper.selectAll();
    }
    
    /**
     * 添加课程
     */
    public boolean add(Course course) {
        course.setStatus(1);
        courseMapper.insert(course);
        return true;
    }
    
    /**
     * 更新课程信息
     */
    public boolean update(Course course) {
        Course existingCourse = courseMapper.selectById(course.getId());
        if (existingCourse == null) {
            return false;
        }
        courseMapper.update(course);
        return true;
    }
    
    /**
     * 删除课程
     */
    public boolean deleteById(Long id) {
        Course course = courseMapper.selectById(id);
        if (course == null) {
            return false;
        }
        courseMapper.deleteById(id);
        return true;
    }
    
    /**
     * 更新课程状态（上架/下架）
     */
    public boolean updateStatus(Long id, Integer status) {
        Course course = courseMapper.selectById(id);
        if (course == null) {
            return false;
        }
        courseMapper.updateStatus(id, status);
        return true;
    }
} 