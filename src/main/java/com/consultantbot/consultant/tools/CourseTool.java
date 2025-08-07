package com.consultantbot.consultant.tools;

import com.consultantbot.consultant.pojo.Course;
import com.consultantbot.consultant.service.CourseService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CourseTool {
    @Autowired
    private CourseService courseService;

    @Tool("查询所有课程")
    public List<Course> getAllCourses() {
        return courseService.getAll();
    }

    @Tool("查询所有上架课程")
    public List<Course> getActiveCourses() {
        return courseService.getAllActive();
    }

    @Tool("根据ID查询课程")
    public Course getCourseById(@P("课程ID") Long id) {
        return courseService.getById(id);
    }

    @Tool("添加新课程")
    public void addCourse(
            @P("课程名称") String name,
            @P("课程描述") String description,
            @P("课程价格") BigDecimal price,
            @P("课程时长(分钟)") Integer duration,
            @P("课程难度") String level
    ) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setPrice(price);
        course.setDuration(duration);
        course.setLevel(level);
        course.setStatus(1);
        courseService.add(course);
    }

    @Tool("更新课程信息")
    public void updateCourse(
            @P("课程ID") Long id,
            @P("课程名称") String name,
            @P("课程描述") String description,
            @P("课程价格") BigDecimal price,
            @P("课程时长(分钟)") Integer duration,
            @P("课程难度") String level
    ) {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setPrice(price);
        course.setDuration(duration);
        course.setLevel(level);
        courseService.update(course);
    }

    @Tool("更新课程状态")
    public void updateCourseStatus(
            @P("课程ID") Long id,
            @P("状态") Integer status
    ) {
        courseService.updateStatus(id, status);
    }

    @Tool("删除课程")
    public void deleteCourse(@P("课程ID") Long id) {
        courseService.deleteById(id);
    }

    @Tool("根据难度查询课程")
    public List<Course> getCoursesByLevel(@P("课程难度") String level) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }

    @Tool("根据价格范围查询课程")
    public List<Course> getCoursesByPriceRange(
            @P("最低价格") BigDecimal minPrice,
            @P("最高价格") BigDecimal maxPrice
    ) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }
} 