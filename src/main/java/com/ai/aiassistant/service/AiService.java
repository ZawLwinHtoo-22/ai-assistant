package com.ai.aiassistant.service;


import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final LlmClient llmClient;

    public AiService(LlmClient llmClient) {
        this.llmClient = llmClient;
    }

    public String chat(String message) {
        return llmClient.callLLM(message);
    }
}