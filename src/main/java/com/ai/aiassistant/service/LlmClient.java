package com.ai.aiassistant.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LlmClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ai.base-url}")
    private String baseUrl;

    @Value("${ai.model}")
    private String model;

    @Value("${ai.temperature}")
    private double temperature;

    public String callLLM(String message) {

        Map<String, Object> request = new HashMap<>();

        request.put("model", model);

        request.put("messages", List.of(
                Map.of("role", "user", "content", message)
        ));

        request.put("temperature", temperature);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        Map response =
                restTemplate.postForObject(baseUrl, entity, Map.class);

        List<Map<String, Object>> choices =
                (List<Map<String, Object>>) response.get("choices");

        Map<String, Object> messageObj =
                (Map<String, Object>) choices.get(0).get("message");

        return messageObj.get("content").toString();
    }
}
