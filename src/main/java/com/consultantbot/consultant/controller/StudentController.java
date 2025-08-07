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
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    /**
     * 学员登录
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        
        Student student = studentService.login(username, password);
        Map<String, Object> response = new HashMap<>();
        
        if (student != null) {
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("data", student);
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 学员注册
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = studentService.register(student);
        if (success) {
            response.put("success", true);
            response.put("message", "注册成功");
        } else {
            response.put("success", false);
            response.put("message", "注册失败，用户名或手机号已存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取学员信息
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
} 