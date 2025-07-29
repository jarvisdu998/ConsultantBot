package com.consultantbot.consultant.controller;

import com.consultantbot.consultant.aiservice.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author jarvisdu
 * @description ChatController
 * @date 2025/7/29
 */

@RestController
public class ChatController {

    @Autowired
    private ConsultantService consultantService;
    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String message) {
        Flux<String> result = consultantService.chat(message);
        return result;
    }



//    @Autowired
//    private OpenAiChatModel model;
//    @RequestMapping("/chat")
//    public String chat(String message){
//        String resust = model.chat(message);
//        return resust;
//}

}
