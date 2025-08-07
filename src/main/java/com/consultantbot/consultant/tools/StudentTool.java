package com.consultantbot.consultant.tools;

import com.consultantbot.consultant.pojo.Student;
import com.consultantbot.consultant.service.StudentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentTool {
    @Autowired
    private StudentService studentService;

    @Tool("学员登录验证")
    public Student studentLogin(
            @P("用户名") String username,
            @P("密码") String password
    ) {
        return studentService.login(username, password);
    }

    @Tool("根据用户名查询学员信息")
    public Student getStudentByUsername(@P("用户名") String username) {
        // 这里需要直接调用Mapper，因为Service中没有对应方法
        return null; // 暂时返回null，实际使用时需要注入Mapper
    }

    @Tool("根据手机号查询学员信息")
    public Student getStudentByPhone(@P("手机号") String phone) {
        // 这里需要直接调用Mapper，因为Service中没有对应方法
        return null; // 暂时返回null，实际使用时需要注入Mapper
    }

    @Tool("查询所有学员信息")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @Tool("根据ID查询学员信息")
    public Student getStudentById(@P("学员ID") Long id) {
        return studentService.getById(id);
    }

    @Tool("添加新学员")
    public void addStudent(
            @P("用户名") String username,
            @P("密码") String password,
            @P("真实姓名") String name,
            @P("性别") String gender,
            @P("手机号") String phone,
            @P("邮箱") String email,
            @P("所在省份") String province
    ) {
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setName(name);
        student.setGender(gender);
        student.setPhone(phone);
        student.setEmail(email);
        student.setProvince(province);
        studentService.register(student);
    }

    @Tool("更新学员信息")
    public void updateStudent(
            @P("学员ID") Long id,
            @P("用户名") String username,
            @P("真实姓名") String name,
            @P("性别") String gender,
            @P("手机号") String phone,
            @P("邮箱") String email,
            @P("所在省份") String province
    ) {
        Student student = new Student();
        student.setId(id);
        student.setUsername(username);
        student.setName(name);
        student.setGender(gender);
        student.setPhone(phone);
        student.setEmail(email);
        student.setProvince(province);
        studentService.update(student);
    }

    @Tool("更新学员状态")
    public void updateStudentStatus(
            @P("学员ID") Long id,
            @P("状态") Integer status
    ) {
        studentService.updateStatus(id, status);
    }

    @Tool("删除学员")
    public void deleteStudent(@P("学员ID") Long id) {
        studentService.deleteById(id);
    }
} 