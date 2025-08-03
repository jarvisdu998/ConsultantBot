package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Instructor;
import com.consultantbot.consultant.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
@CrossOrigin(origins = "*")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    /**
     * 获取所有讲师
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllInstructors() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Instructor> instructors = instructorService.getAllInstructors();
            response.put("success", true);
            response.put("data", instructors);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取讲师列表失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据ID获取讲师详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInstructorById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Instructor instructor = instructorService.getInstructorById(id);
            if (instructor != null) {
                response.put("success", true);
                response.put("data", instructor);
            } else {
                response.put("success", false);
                response.put("message", "讲师不存在");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取讲师详情失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 