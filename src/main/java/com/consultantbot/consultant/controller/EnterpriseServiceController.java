package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.EnterpriseService;
import com.consultantbot.consultant.service.EnterpriseServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enterprise")
@CrossOrigin(origins = "*")
public class EnterpriseServiceController {

    @Autowired
    private EnterpriseServiceService enterpriseServiceService;

    /**
     * 获取所有企业服务
     */
    @GetMapping("/services")
    public ResponseEntity<Map<String, Object>> getAllServices() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<EnterpriseService> services = enterpriseServiceService.getAllServices();
            response.put("success", true);
            response.put("data", services);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取服务列表失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据分类获取企业服务
     */
    @GetMapping("/services/category/{category}")
    public ResponseEntity<Map<String, Object>> getServicesByCategory(@PathVariable String category) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<EnterpriseService> services = enterpriseServiceService.getServicesByCategory(category);
            response.put("success", true);
            response.put("data", services);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取服务列表失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 根据ID获取企业服务详情
     */
    @GetMapping("/services/{id}")
    public ResponseEntity<Map<String, Object>> getServiceById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            EnterpriseService service = enterpriseServiceService.getServiceById(id);
            if (service != null) {
                response.put("success", true);
                response.put("data", service);
            } else {
                response.put("success", false);
                response.put("message", "服务不存在");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取服务详情失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 