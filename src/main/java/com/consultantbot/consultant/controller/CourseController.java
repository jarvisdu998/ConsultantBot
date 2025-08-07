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
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    /**
     * 获取所有上架课程（学员端）
     */
    @GetMapping("/active")
    public ResponseEntity<Map<String, Object>> getActiveCourses() {
        List<Course> courses = courseService.getAllActive();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", courses);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取所有课程（管理员端）
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        List<Course> courses = courseService.getAll();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", courses);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取课程详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCourse(@PathVariable Long id) {
        Course course = courseService.getById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (course != null) {
            response.put("success", true);
            response.put("data", course);
        } else {
            response.put("success", false);
            response.put("message", "课程不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 添加课程
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addCourse(@RequestBody Course course) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = courseService.add(course);
        if (success) {
            response.put("success", true);
            response.put("message", "添加成功");
        } else {
            response.put("success", false);
            response.put("message", "添加失败");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 更新课程信息
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        Map<String, Object> response = new HashMap<>();
        
        boolean success = courseService.update(course);
        if (success) {
            response.put("success", true);
            response.put("message", "更新成功");
        } else {
            response.put("success", false);
            response.put("message", "更新失败，课程不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 删除课程
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = courseService.deleteById(id);
        if (success) {
            response.put("success", true);
            response.put("message", "删除成功");
        } else {
            response.put("success", false);
            response.put("message", "删除失败，课程不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 更新课程状态（上架/下架）
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateCourseStatus(@PathVariable Long id, @RequestBody Map<String, Integer> statusData) {
        Integer status = statusData.get("status");
        Map<String, Object> response = new HashMap<>();
        
        boolean success = courseService.updateStatus(id, status);
        if (success) {
            response.put("success", true);
            response.put("message", "状态更新成功");
        } else {
            response.put("success", false);
            response.put("message", "状态更新失败，课程不存在");
        }
        
        return ResponseEntity.ok(response);
    }
} 