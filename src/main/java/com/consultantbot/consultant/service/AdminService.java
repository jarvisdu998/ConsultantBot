package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.AdminMapper;
import com.consultantbot.consultant.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    
    /**
     * 管理员登录
     */
    public Admin login(String username, String password) {
        Admin admin = adminMapper.selectByUsername(username);
        if (admin != null && admin.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return admin;
        }
        return null;
    }
    
    /**
     * 根据ID查询管理员
     */
    public Admin getById(Long id) {
        return adminMapper.selectById(id);
    }
    
    /**
     * 查询所有管理员
     */
    public List<Admin> getAll() {
        return adminMapper.selectAll();
    }
    
    /**
     * 添加管理员
     */
    public boolean add(Admin admin) {
        // 检查用户名是否已存在
        if (adminMapper.selectByUsername(admin.getUsername()) != null) {
            return false;
        }
        // 密码加密
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adminMapper.insert(admin);
        return true;
    }
    
    /**
     * 更新管理员信息
     */
    public boolean update(Admin admin) {
        Admin existingAdmin = adminMapper.selectById(admin.getId());
        if (existingAdmin == null) {
            return false;
        }
        // 如果密码没有变化，保持原密码
        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            admin.setPassword(existingAdmin.getPassword());
        } else {
            admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        }
        adminMapper.update(admin);
        return true;
    }
    
    /**
     * 删除管理员
     */
    public boolean deleteById(Long id) {
        Admin admin = adminMapper.selectById(id);
        if (admin == null) {
            return false;
        }
        adminMapper.deleteById(id);
        return true;
    }
} 