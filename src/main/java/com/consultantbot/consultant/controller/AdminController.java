package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.Admin;
import com.consultantbot.consultant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        
        Admin admin = adminService.login(username, password);
        Map<String, Object> response = new HashMap<>();
        
        if (admin != null) {
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("data", admin);
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取所有管理员
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAdmins() {
        List<Admin> admins = adminService.getAll();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", admins);
        return ResponseEntity.ok(response);
    }
    
    /**
     * 获取管理员信息
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAdmin(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        Map<String, Object> response = new HashMap<>();
        
        if (admin != null) {
            response.put("success", true);
            response.put("data", admin);
        } else {
            response.put("success", false);
            response.put("message", "管理员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 添加管理员
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addAdmin(@RequestBody Admin admin) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = adminService.add(admin);
        if (success) {
            response.put("success", true);
            response.put("message", "添加成功");
        } else {
            response.put("success", false);
            response.put("message", "添加失败，用户名已存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 更新管理员信息
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        Map<String, Object> response = new HashMap<>();
        
        boolean success = adminService.update(admin);
        if (success) {
            response.put("success", true);
            response.put("message", "更新成功");
        } else {
            response.put("success", false);
            response.put("message", "更新失败，管理员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 删除管理员
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteAdmin(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        boolean success = adminService.deleteById(id);
        if (success) {
            response.put("success", true);
            response.put("message", "删除成功");
        } else {
            response.put("success", false);
            response.put("message", "删除失败，管理员不存在");
        }
        
        return ResponseEntity.ok(response);
    }
} 