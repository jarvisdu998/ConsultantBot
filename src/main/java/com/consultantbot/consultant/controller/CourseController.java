package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Course;
import com.consultantbot.consultant.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取所有课程
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Course> courses = courseService.getAllCourses();
            response.put("success", true);
            response.put("data", courses);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取课程列表失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据分类获取课程
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> getCoursesByCategory(@PathVariable String category) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Course> courses = courseService.getCoursesByCategory(category);
            response.put("success", true);
            response.put("data", courses);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取课程列表失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 获取热门课程
     */
    @GetMapping("/popular")
    public ResponseEntity<Map<String, Object>> getPopularCourses() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Course> courses = courseService.getPopularCourses();
            response.put("success", true);
            response.put("data", courses);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取热门课程失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据ID获取课程详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCourseById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Course course = courseService.getCourseById(id);
            if (course != null) {
                response.put("success", true);
                response.put("data", course);
            } else {
                response.put("success", false);
                response.put("message", "课程不存在");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取课程详情失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 