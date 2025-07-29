package com.consultantbot.consultant.controller;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jarvisdu
 * @description ChatController
 * @date 2025/7/29
 */

@RestController
public class ChatController {

    @Autowired
    private OpenAiChatModel model;
    @RequestMapping("/chat")
    public String chat(String message){
        String resust = model.chat(message);
        return resust;
    }
}
