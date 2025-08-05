package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.UserMapper;
import com.consultantbot.consultant.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    // 用户注册
    public boolean register(User user) {
        // 检查用户名是否已存在
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false;
        }
        
        // 检查手机号是否已存在
        if (userMapper.findByPhone(user.getPhone()) != null) {
            return false;
        }
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("student"); // 默认角色为学员
        user.setStatus(1); // 默认状态为正常
        
        return userMapper.insert(user) > 0;
    }
    
    // 用户登录
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
    
    // 根据ID查询用户
    public User findById(Long id) {
        return userMapper.findById(id);
    }
    
    // 根据用户名查询用户
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    
    // 根据手机号查询用户
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }
    
    // 查询所有用户
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    // 根据角色查询用户
    public List<User> findByRole(String role) {
        return userMapper.findByRole(role);
    }
    
    // 更新用户信息
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }
    
    // 删除用户
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }
    
    // 检查用户是否为管理员
    public boolean isAdmin(User user) {
        return user != null && "admin".equals(user.getRole());
    }
    
    // 检查用户是否为学员
    public boolean isStudent(User user) {
        return user != null && "student".equals(user.getRole());
    }
} 