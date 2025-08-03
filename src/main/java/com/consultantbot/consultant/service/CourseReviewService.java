package com.consultantbot.consultant.service;

import com.consultantbot.consultant.mapper.CourseReviewMapper;
import com.consultantbot.consultant.pojo.CourseReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseReviewService {
    @Autowired
    private CourseReviewMapper courseReviewMapper;
    
    // 根据课程ID查询评价
    public List<CourseReview> getReviewsByCourseId(Long courseId) {
        return courseReviewMapper.selectByCourseId(courseId);
    }
    
    // 添加评价
    public void addReview(CourseReview review) {
        courseReviewMapper.insert(review);
    }
    
    // 查询课程平均评分
    public Double getAverageRatingByCourseId(Long courseId) {
        return courseReviewMapper.selectAverageRatingByCourseId(courseId);
    }
    
    // 查询课程评价数量
    public Integer getReviewCountByCourseId(Long courseId) {
        return courseReviewMapper.selectCountByCourseId(courseId);
    }
} 