package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.InstructorMapper;
import com.consultantbot.consultant.pojo.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorMapper instructorMapper;
    
    // 查询所有在职讲师
    public List<Instructor> getAllInstructors() {
        return instructorMapper.selectAll();
    }
    
    // 根据ID查询讲师
    public Instructor getInstructorById(Long id) {
        return instructorMapper.selectById(id);
    }
} 