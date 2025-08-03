package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.EnterpriseServiceMapper;
import com.consultantbot.consultant.pojo.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseServiceService {
    @Autowired
    private EnterpriseServiceMapper enterpriseServiceMapper;
    
    // 查询所有可用服务
    public List<EnterpriseService> getAllServices() {
        return enterpriseServiceMapper.selectAll();
    }
    
    // 根据分类查询服务
    public List<EnterpriseService> getServicesByCategory(String category) {
        return enterpriseServiceMapper.selectByCategory(category);
    }
    
    // 根据ID查询服务
    public EnterpriseService getServiceById(Long id) {
        return enterpriseServiceMapper.selectById(id);
    }
} 