package com.consultantbot.consultant.tools;

import com.consultantbot.consultant.pojo.Admin;
import com.consultantbot.consultant.service.AdminService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminTool {
    @Autowired
    private AdminService adminService;

    @Tool("管理员登录验证")
    public Admin adminLogin(
            @P("用户名") String username,
            @P("密码") String password
    ) {
        return adminService.login(username, password);
    }

    @Tool("根据用户名查询管理员信息")
    public Admin getAdminByUsername(@P("用户名") String username) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }

    @Tool("根据ID查询管理员信息")
    public Admin getAdminById(@P("管理员ID") Long id) {
        return adminService.getById(id);
    }

    @Tool("查询所有管理员")
    public List<Admin> getAllAdmins() {
        return adminService.getAll();
    }

    @Tool("添加新管理员")
    public void addAdmin(
            @P("用户名") String username,
            @P("密码") String password,
            @P("真实姓名") String name,
            @P("手机号") String phone,
            @P("邮箱") String email
    ) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setName(name);
        admin.setPhone(phone);
        admin.setEmail(email);
        adminService.add(admin);
    }

    @Tool("更新管理员信息")
    public void updateAdmin(
            @P("管理员ID") Long id,
            @P("用户名") String username,
            @P("真实姓名") String name,
            @P("手机号") String phone,
            @P("邮箱") String email
    ) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setUsername(username);
        admin.setName(name);
        admin.setPhone(phone);
        admin.setEmail(email);
        adminService.update(admin);
    }

    @Tool("删除管理员")
    public void deleteAdmin(@P("管理员ID") Long id) {
        adminService.deleteById(id);
    }

    @Tool("根据手机号查询管理员")
    public Admin getAdminByPhone(@P("手机号") String phone) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }

    @Tool("根据邮箱查询管理员")
    public Admin getAdminByEmail(@P("邮箱") String email) {
        // 这个方法在Service中没有，暂时返回null
        return null;
    }
} 