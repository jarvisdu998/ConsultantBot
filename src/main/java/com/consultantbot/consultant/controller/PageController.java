package com.consultantbot.consultant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    
    /**
     * 根路径重定向到登录页面
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/login.html";
    }
    
    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    
    /**
     * 主页
     */
    @GetMapping("/index")
    public String index() {
        return "index.html";
    }
} 