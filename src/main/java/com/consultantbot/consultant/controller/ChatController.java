package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.aiservice.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jarvisdu
 * @description ChatController
 * @date 2025/7/29
 */

@RestController
public class ChatController {

    @Autowired
    private ConsultantService consultantService;
    
    // 简单的登录状态管理（实际项目中应该使用更安全的方式）
    private static final ConcurrentHashMap<String, Boolean> loginStatus = new ConcurrentHashMap<>();
    
    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam String memoryId, @RequestParam String message, HttpSession session) {
        // 检查用户是否已登录
        String sessionId = session.getId();
        Boolean isLoggedIn = loginStatus.get(sessionId);
        
        if (isLoggedIn == null || !isLoggedIn) {
            // 用户未登录，返回登录提示
            return Flux.just("抱歉，您还没有登录。请先登录后再使用聊天功能。\n\n您可以访问登录页面：http://localhost:8080/login.html");
        }
        
        // 用户已登录，正常处理聊天
        Flux<String> result = consultantService.chat(memoryId, message);
        return result;
    }
    
    /**
     * 设置用户登录状态
     */
    @RequestMapping(value = "/api/setLoginStatus", method = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    public String setLoginStatus(@RequestParam(required = false) String status, HttpSession session) {
        String sessionId = session.getId();
        // 如果没有传递status参数，默认为true
        String statusValue = status != null ? status : "true";
        Boolean loginStatusValue = Boolean.parseBoolean(statusValue);
        loginStatus.put(sessionId, loginStatusValue);
        System.out.println("设置登录状态: sessionId=" + sessionId + ", status=" + statusValue + ", result=" + loginStatusValue);
        return "{\"success\": true, \"message\": \"登录状态已更新\"}";
    }
    
    /**
     * 清除用户登录状态（登出）
     */
    @RequestMapping("/api/logout")
    public String logout(HttpSession session) {
        String sessionId = session.getId();
        loginStatus.remove(sessionId);
        session.invalidate();
        return "{\"success\": true, \"message\": \"已成功登出\"}";
    }



//    @Autowired
//    private OpenAiChatModel model;
//    @RequestMapping("/chat")
//    public String chat(String message){
//        String resust = model.chat(message);
//        return resust;
//}

}
