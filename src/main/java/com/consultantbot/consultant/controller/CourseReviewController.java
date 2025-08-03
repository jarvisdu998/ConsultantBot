package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.pojo.CourseReview;
import com.consultantbot.consultant.service.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "*")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    /**
     * 根据课程ID获取评价
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Map<String, Object>> getReviewsByCourseId(@PathVariable Long courseId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<CourseReview> reviews = courseReviewService.getReviewsByCourseId(courseId);
            Double averageRating = courseReviewService.getAverageRatingByCourseId(courseId);
            Integer reviewCount = courseReviewService.getReviewCountByCourseId(courseId);
            
            response.put("success", true);
            response.put("data", reviews);
            response.put("averageRating", averageRating);
            response.put("reviewCount", reviewCount);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取评价失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 添加评价
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addReview(@RequestBody CourseReview review) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            courseReviewService.addReview(review);
            response.put("success", true);
            response.put("message", "评价提交成功");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "评价提交失败：" + e.getMessage());
        }
        
        return ResponseEntity.ok(response);
    }
} 