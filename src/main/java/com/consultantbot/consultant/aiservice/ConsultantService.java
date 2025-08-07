package com.consultantbot.consultant.aiservice;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import reactor.core.publisher.Flux;

/**
 * @author jarvisdu
 * @description
 * @date 2025/7/29
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,// 手动装配
        chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel",
        chatMemory = "chatMemory", // 配置会话记忆对象
        chatMemoryProvider = "chatMemoryProvider",
        contentRetriever = "contentRetriever",
        tools = {"reservationTool", "studentTool", "courseTool", "orderTool", "adminTool"}
)

public interface ConsultantService {

    //public String chat(String message);
    //@SystemMessage("你是码上启航工作室的老板杜嘉伟的小助手，负责和用户进行沟通高考志愿填报业务相关的事宜。")
    @SystemMessage(fromResource = "prompt\\system.txt") // 绝对路径
    //@UserMessage("")
    public Flux<String> chat(@MemoryId String memoryId, @UserMessage String message);
}
