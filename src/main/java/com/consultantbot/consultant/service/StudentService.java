package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.StudentMapper;
import com.consultantbot.consultant.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class StudentService {
    
    @Autowired
    private StudentMapper studentMapper;
    
    /**
     * 学员登录
     */
    public Student login(String username, String password) {
        Student student = studentMapper.selectByUsername(username);
        if (student != null && student.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return student;
        }
        return null;
    }
    
    /**
     * 学员注册
     */
    public boolean register(Student student) {
        // 检查用户名是否已存在
        if (studentMapper.selectByUsername(student.getUsername()) != null) {
            return false;
        }
        // 检查手机号是否已存在
        if (studentMapper.selectByPhone(student.getPhone()) != null) {
            return false;
        }
        // 密码加密
        student.setPassword(DigestUtils.md5DigestAsHex(student.getPassword().getBytes()));
        student.setStatus(1);
        studentMapper.insert(student);
        return true;
    }
    
    /**
     * 根据ID查询学员
     */
    public Student getById(Long id) {
        return studentMapper.selectById(id);
    }
    
    /**
     * 查询所有学员
     */
    public List<Student> getAll() {
        return studentMapper.selectAll();
    }
    
    /**
     * 更新学员信息
     */
    public boolean update(Student student) {
        Student existingStudent = studentMapper.selectById(student.getId());
        if (existingStudent == null) {
            return false;
        }
        // 如果密码没有变化，保持原密码
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword(existingStudent.getPassword());
        } else {
            student.setPassword(DigestUtils.md5DigestAsHex(student.getPassword().getBytes()));
        }
        studentMapper.update(student);
        return true;
    }
    
    /**
     * 删除学员
     */
    public boolean deleteById(Long id) {
        Student student = studentMapper.selectById(id);
        if (student == null) {
            return false;
        }
        studentMapper.deleteById(id);
        return true;
    }
    
    /**
     * 更新学员状态
     */
    public boolean updateStatus(Long id, Integer status) {
        Student student = studentMapper.selectById(id);
        if (student == null) {
            return false;
        }
        studentMapper.updateStatus(id, status);
        return true;
    }
} 