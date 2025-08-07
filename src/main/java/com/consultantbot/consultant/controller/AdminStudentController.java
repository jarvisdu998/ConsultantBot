package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Student;
import com.consultantbot.consultant.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/student")
public class AdminStudentController {
    
    @Autowired
    private StudentService studentService;
    
    /**
     * 获取所有学员
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        List<Student> students = studentService.getAll();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", students);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取学员详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudent(@PathVariable Long id) {
        Student student = studentService.getById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (student != null) {
            response.put("success", true);
            response.put("data", student);
        } else {
            response.put("success", false);
            response.put("message", "学员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 添加学员
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = studentService.register(student);
        if (success) {
            response.put("success", true);
            response.put("message", "添加成功");
        } else {
            response.put("success", false);
            response.put("message", "添加失败，用户名或手机号已存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 更新学员信息
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        Map<String, Object> response = new HashMap<>();
        
        boolean success = studentService.update(student);
        if (success) {
            response.put("success", true);
            response.put("message", "更新成功");
        } else {
            response.put("success", false);
            response.put("message", "更新失败，学员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 删除学员
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = studentService.deleteById(id);
        if (success) {
            response.put("success", true);
            response.put("message", "删除成功");
        } else {
            response.put("success", false);
            response.put("message", "删除失败，学员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 更新学员状态
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateStudentStatus(@PathVariable Long id, @RequestBody Map<String, Integer> statusData) {
        Integer status = statusData.get("status");
        Map<String, Object> response = new HashMap<>();
        
        boolean success = studentService.updateStatus(id, status);
        if (success) {
            response.put("success", true);
            response.put("message", "状态更新成功");
        } else {
            response.put("success", false);
            response.put("message", "状态更新失败，学员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
} 