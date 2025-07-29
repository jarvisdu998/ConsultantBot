package com.consultantbot.consultant.aiservice;

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
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel"
)

public interface ConsultantService {

    //public String chat(String message);
    @SystemMessage("你是码上启航工作室的老板杜嘉伟的小助手，负责和询单的用户进行沟通业务相关的事宜。")
    //@SystemMessage(fromResource = "prompt.txt")
    //@UserMessage("")
    public Flux<String> chat(String message);
}
