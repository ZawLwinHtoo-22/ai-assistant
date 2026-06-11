package com.ai.aiassistant.controller;

import com.ai.aiassistant.dto.ChatRequest;
import com.ai.aiassistant.dto.ChatResponse;
import com.ai.aiassistant.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final AiService aiService;

    public ChatController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String reply = aiService.chat(request.getMessage());
        return new ChatResponse(reply);
    }
}
