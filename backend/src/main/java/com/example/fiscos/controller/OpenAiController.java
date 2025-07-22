package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.*;

import com.example.fiscos.service.openAi.OpenAiService;

@RestController
@RequestMapping("/ia")
public class OpenAiController {

    private final OpenAiService openAiService;

    public OpenAiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/prompt")
    public String prompt(@RequestBody String prompt) {
        return openAiService.sendPrompt(prompt);
    }
}

